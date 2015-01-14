package ua.kpi.eec.vml.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import ua.kpi.eec.vml.model.dto.AccountData;
import ua.kpi.eec.vml.model.entity.SystemRole;

public class VmlAuthenticationManager implements AuthenticationManager {

	private AuthenticationStrategy authenticationStrategy;
	
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		AccountData principal = authenticationStrategy.authenticate(auth.getName(), auth.getCredentials().toString());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// TODO refresh user data
		authorities.add(SystemRole.USER);
		return new UsernamePasswordAuthenticationToken(principal, auth.getCredentials(), authorities);
	}

	@Required
	public void setAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
		this.authenticationStrategy = authenticationStrategy;
	}
	
}
