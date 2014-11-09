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

import ua.kpi.eec.vml.model.entity.Account;
import ua.kpi.eec.vml.model.entity.SystemRole;
import ua.kpi.eec.vml.service.MoodleRequestException;
import ua.kpi.eec.vml.service.MoodleService;

public class MoodleAuthenticationManager implements AuthenticationManager {

    private MoodleService moodleService;

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
	try {
	    Account account = moodleService.authenticate(auth.getName(), auth.getCredentials().toString());
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    if(account.getRole()!=null)
		authorities.add(account.getRole());
	    //TODO implement mock
	    authorities.add(SystemRole.USER);
	    return new UsernamePasswordAuthenticationToken(account,
		    auth.getCredentials(), authorities);
	} catch (MoodleRequestException e) {
	    throw new BadCredentialsException("Login failed", e);
	}
    }

    @Required
    public void setMoodleService(MoodleService moodleService) {
	this.moodleService = moodleService;
    }

}
