package ua.kpi.eec.vml.service.authentication;

import org.springframework.security.authentication.BadCredentialsException;

import ua.kpi.eec.vml.model.dto.AccountData;

public class MockAuthenticationStrategy implements AuthenticationStrategy {

	@Override
	public AccountData authenticate(String name, String credentials)
			throws BadCredentialsException {
		if (!name.equals(credentials))
			throw new BadCredentialsException("The username or password is wrong");
		AccountData principal = new AccountData();
		principal.setUsername(name);
		principal.setFullName(name + " " + name);
		principal.setMoodleId(0);
		return principal;
	}
}
