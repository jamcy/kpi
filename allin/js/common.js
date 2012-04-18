//vk
function vk_login()
{
		VK.init({apiId: 2900700});
		VK.Auth.getLoginStatus(authInfo, 3072);
}

function authInfo(response) {
  if (response.session) {
    //alert('user: '+response.session.mid);
    	$("#vk").load('?r=login/vk&auth='+response.session.mid);
  } else {
    	$("#vk").load(0);
  }
}

function fb_login()
{
	$('#fb').load('?r=login/fb');
}
function twi_login()
{
	$('#twi').load('?r=login/twi');
}


$(document).ready(function(){ 
     	//activating counting of chars
      $("#textarea").charCount();
      //logging to vk
      $(fb_login);
      $(vk_login);
      //$(twi_login);      
      
   });
   
   i=0;
   function lol() {
      i++;
      if (i%2==1) $('.btn-vk').button('olol');
      else $('.btn-vk').button('reset');
   }