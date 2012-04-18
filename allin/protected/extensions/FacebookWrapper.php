<?php
  class FacebookWrapper extends SocialWrapper
  {
  	 function init(){}
    public function Post($params)
    {
      $post = array('message'=>$params);
      Yii::app()->facebook->api('/me/feed', 'POST', $post);
    }
    function getName()
    {
    	return "Facebook";
    }
    function isPermissions($params)
    {
    }
    function Login()
    {
    try
    {
      if(Yii::app()->facebook->getUser()==0)
        return 0;
      else
      {
        $perms = Yii::app()->facebook->api('/me/permissions');
        $perms = $perms['data'][0];
        if(array_key_exists('publish_stream', $perms))
          return Yii::app()->facebook->getUser();
        else
          return 0;
      }
    } catch(Exception $ex)
    {
      return 0;
    }
  }