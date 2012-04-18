<?php

class LoginController extends Controller
{
	public function actionIndex()
	{
		
	}
	public function actionVk()
	{
		if(isset($_GET['auth']))
		{
			if($_GET['auth']>0)
				echo 'Logged as'.$_GET['auth'];
			else
				echo '<a class="login" id="vk_login">Login</a>';
		}
	}
	
	public function actionFb()
	{
		echo Yii::app()->fbAgg->getName();
		/*try
		{
			Yii::import('ext.FacebookWrapper');
			$result = Yii::app()->fbAgg->Login();
			echo $result;
		}catch(Exception $ex)
		{
			echo $ex;
		}*/
	}
	
	public function actionTwi()
	{
		echo "Twitter must be here";
	}
}