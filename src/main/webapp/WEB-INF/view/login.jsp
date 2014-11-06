<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page language="java" pageEncoding="UTF-8"%>

<t:master>
<jsp:body>
<div id="present" class=" present">		
	<div id="vml3d" class="container"></div>
	<img src="<spring:url value="/images/present_login.jpg"/>" />
	<div class="container">
		<div class="over">
			<div class="row">
				<div class="col-md-4 col-md-offset-3 center">
					<h1>Login</h1>
					<form class="form-horizontal" role="form" id="login-form"
							action="<spring:url value='/j_spring_security_check' />" method="post">
						<c:if test="${not empty error }">
						<div class="form-alert">
							<spring:message code="${error }" />
						</div>
						</c:if>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-4 control-label"><spring:message code="form.login.username" /></label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="inputEmail3"
											name="j_username" value="<%-- TOODO: popula --%>"
											placeholder="<spring:message code="form.login.username" />" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-4 control-label"><spring:message code="form.login.password" /></label>
							<div class="col-sm-8">
								<input type="password" class="form-control"
									id="inputPassword3" name="j_password"
									value=""
									placeholder="<spring:message code="form.login.password" />" />
							</div>
						</div> 
						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-8">
								<button type="submit" class="btn btn-primary pull-left"><spring:message code="form.button.confirm" /></button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>						
	</div>
</div>
</jsp:body>
</t:master>
