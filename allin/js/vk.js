$(
	function(){
		VK.init({apiId: 2900700});
		VK.Auth.getLoginStatus(authInfo);
		VK.UI.button('login_button');
});

function authInfo(response) {
  if (response.session) {
    alert('user: '+response.session.mid);
  } else {
    alert('not auth');
  }
}


