
<%@page import="model.form.LoginForm"%>
<%
	LoginForm data = (LoginForm)request.getAttribute("form-data");
%>

<div id="present" class=" present">		
		<div id="vml3d" class="container"></div>
		<img src="/images/present_login.jpg"/>
		<div class="container">
			<div class="over">
				
				<div class="row">
					<div class="col-md-4 col-md-offset-3 center">
						<h1>Login</h1>
						<form class="form-horizontal" role="form" id="login-form" action="" method="post">
							<%if(data.getMessage()!=null&&!data.getMessage().equals("")) { %>
								<div class="form-alert"><%=messages.getString(data.getMessage()) %></div>
							<%} %>
						  <div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label"><%=messages.getString("form.login.username") %></label>
						    <div class="col-sm-8">
						      <input type="text" class="form-control" id="inputEmail3" 
						      		 name="username"  value="<%=data.getUsername()%>"
						      		 placeholder="<%=messages.getString("form.login.username") %>">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="inputPassword3" class="col-sm-4 control-label"><%=messages.getString("form.login.password") %></label>
						    <div class="col-sm-8">
						      <input type="password" class="form-control" id="inputPassword3" 
						      		 name="password" value="<%=data.getPassword() %>"
						      		 placeholder="<%=messages.getString("form.login.password") %>">
						    </div>
						  </div> 
						  <div class="form-group">
						    <div class="col-sm-offset-4 col-sm-8">
						      <button type="submit" class="btn btn-primary pull-left"><%=messages.getString("form.button.confirm")%></button>
						    </div>
						  </div>
						</form>

					</div>
				</div>

			</div>						
		</div>
</div>
