<?php
    Yii::import('ext.twitter.tmhUtilities');
    Yii::import('ext.twitter.tmhOAuth');
    Yii::import('ext.SocialWrapper'); 
    class TwitterWrapper extends SocialWrapper
    {
		function init(){}
        function Post($params)
        {
                $tmhOAuth = new tmhOAuth(array(
				  'consumer_key'    => 'Npvej2chz2fmEJRZPrUPQ',
				  'consumer_secret' => 'MmRgVQc2KHPa1AXf5aWSJMShmwEB79mtkYvc8',
				  'user_token'      => '485615251-O0zBTAm1TMDUJaktYQva4JOMAcmozqR6Eti3Usyp',
				  'user_secret'     => 'JekIYd0Raxdg3vvAvDCwIaRX1JCt2Exqr8m17eZZk',
				));
			
			
			$code = $tmhOAuth->request('POST', $tmhOAuth->url('1/statuses/update'), array(
			  'status' => $params['status']	
			));
			return $code;
        }
        function Login()
        { 
			$tmhOAuth = new tmhOAuth(array(
			  'consumer_key'    => 'Npvej2chz2fmEJRZPrUPQ',
			  
			  'consumer_secret' => 'MmRgVQc2KHPa1AXf5aWSJMShmwEB79mtkYvc8',
			));
			
			$here = tmhUtilities::php_self();
			//session_save_path('/var');
			session_start();
			/*function outputError($tmhOAuth) {
			  echo 'Error: ' . $tmhOAuth->response['response'] . PHP_EOL;
			  tmhUtilities::pr($tmhOAuth);
			}*/
			
			// reset request?
			if ( isset($_REQUEST['wipe'])) {
			  session_destroy();

			  header("Location: {$here}");
			
			// already got some credentials stored?
			} elseif ( isset($_SESSION['access_token']) ) {
			  $tmhOAuth->config['user_token']  = $_SESSION['access_token']['oauth_token'];
			  $tmhOAuth->config['user_secret'] = $_SESSION['access_token']['oauth_token_secret'];
			
			  $code = $tmhOAuth->request('GET', $tmhOAuth->url('1/account/verify_credentials'));
			  if ($code == 200) {
				$resp = json_decode($tmhOAuth->response['response']);
				echo $resp->screen_name;
			  } else {
				throw new CHttpException(404,$tmhOAuth->response['response']);
				//outputError($tmhOAuth);
			  }
			// we're being called back by Twitter
			} elseif (isset($_REQUEST['oauth_verifier'])) {
			  $tmhOAuth->config['user_token']  = $_SESSION['oauth']['oauth_token'];
			  $tmhOAuth->config['user_secret'] = $_SESSION['oauth']['oauth_token_secret'];
			
			  $code = $tmhOAuth->request('POST', $tmhOAuth->url('oauth/access_token', ''), array(
				'oauth_verifier' => $_REQUEST['oauth_verifier']
			  ));
			
			  if ($code == 200) {
				$_SESSION['access_token'] = $tmhOAuth->extract_params($tmhOAuth->response['response']);
				unset($_SESSION['oauth']);
				header("Location: {$here}");
			  } else {
				throw new CHttpException(404,$tmhOAuth->response['response']);
				//outputError($tmhOAuth);
			  }
			// start the OAuth dance
			} elseif ( isset($_REQUEST['authenticate']) || isset($_REQUEST['authorize']) ) {
			  $callback = isset($_REQUEST['oob']) ? 'oob' : $here;
			
			  $params = array(
				'oauth_callback'     => $callback
			  );
			
			  if (isset($_REQUEST['force_write'])) :
				$params['x_auth_access_type'] = 'write';
			  elseif (isset($_REQUEST['force_read'])) :
				$params['x_auth_access_type'] = 'read';
			  endif;
			
			  $code = $tmhOAuth->request('POST', $tmhOAuth->url('oauth/request_token', ''), $params);
			
			  if ($code == 200) {
				$_SESSION['oauth'] = $tmhOAuth->extract_params($tmhOAuth->response['response']);
				$method = isset($_REQUEST['authenticate']) ? 'authenticate' : 'authorize';
				$force  = isset($_REQUEST['force']) ? '&force_login=1' : '';
				$authurl = $tmhOAuth->url("oauth/{$method}", '') .  "?oauth_token={$_SESSION['oauth']['oauth_token']}{$force}";
				return $authurl;
				//echo '<p>To complete the OAuth flow follow this URL: <a href="'. $authurl . '">' . $authurl . '</a></p>';
				
			  } else {
				throw new CHttpException(404,$tmhOAuth->response['response']);
				//outputError($tmhOAuth);
			  }
			}
        }
    }
