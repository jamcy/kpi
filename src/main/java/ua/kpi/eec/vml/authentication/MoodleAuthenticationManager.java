package ua.kpi.eec.vml.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import ua.kpi.eec.vml.model.dto.AccountData;
import ua.kpi.eec.vml.model.entity.SystemRole;
import ua.kpi.eec.vml.service.MoodleRequestException;
import ua.kpi.eec.vml.service.MoodleService;

public class MoodleAuthenticationManager implements AuthenticationManager {

	private MoodleService moodleService;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		try {
			AccountData principal = moodleService.authenticate(auth.getName(), auth.getCredentials().toString());
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			// TODO remove mock 
			// TODO refresh user data
			authorities.add(SystemRole.USER);
			return new UsernamePasswordAuthenticationToken(principal, auth.getCredentials(), authorities);
		} catch (MoodleRequestException e) {
			throw new BadCredentialsException("Login failed", e);
		}
	}

	@Required
	public void setMoodleService(MoodleService moodleService) {
		this.moodleService = moodleService;
	}

}
