package ua.kpi.eec.vml.service.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;

import ua.kpi.eec.vml.model.dto.AccountData;
import ua.kpi.eec.vml.model.entity.SystemRole;

public class MockAuthenticationStrategy implements AuthenticationStrategy {
	
	private Map<String, String> users = new HashMap<String, String>();
	private SystemRole role = SystemRole.USER;
	
	@Override
	public AccountData authenticate(String name, String credentials)
			throws BadCredentialsException {
		if(users.containsKey(name)&&users.get(name).equals(credentials)) {
			AccountData principal = new AccountData();
			principal.setMoodleId(0);
			principal.setUsername(name);
			principal.setFullName(name);
			principal.setRole(role);
			return principal;
		}
		throw new BadCredentialsException("The username or password is wrong");
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}

	public void setRole(SystemRole role) {
		this.role = role;
	}
}
