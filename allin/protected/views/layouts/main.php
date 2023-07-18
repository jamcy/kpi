<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />

	<link rel="stylesheet" type="text/css" href="<?php echo Yii::app()->request->baseUrl; ?>/css/main.css" />
   <link rel="stylesheet" type="text/css" href="<?php echo Yii::app()->request->baseUrl; ?>/css/font-awesome.css" />


   <title>Allin</title>
</head>

<body>
  <div class="container" id="page">
    <div id="header">
      <div id="logo"><a href=<?php echo Yii::app()->request->baseUrl;?>><img src='images/logo.jpg' width=150/></a>Let the pony be with you!</div>
    </div><!-- header -->

    <div id="content">
    <?php echo $content; ?>
    </div><!-- content -->

    <div class="clear"></div>

    <div id="footer">
		
    </div><!-- footer -->

</div><!-- page -->

</body>
</html>
