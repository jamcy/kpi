package ua.kpi.eec.vml.service.authentication;

import org.springframework.security.authentication.BadCredentialsException;

import ua.kpi.eec.vml.model.dto.AccountData;

public interface AuthenticationStrategy {
	AccountData authenticate(String name, String credentials) throws BadCredentialsException;
}
