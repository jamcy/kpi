package ua.kpi.eec.vml.service.authentication;

import javax.annotation.Resource;

import org.springframework.security.authentication.BadCredentialsException;

import ua.kpi.eec.vml.model.dto.AccountData;
import ua.kpi.eec.vml.service.MoodleRequestException;
import ua.kpi.eec.vml.service.MoodleService;

public class MoodleAuthenticationStrategy implements AuthenticationStrategy {

	@Resource
	private MoodleService moodleService;

	@Override
	public AccountData authenticate(String name, String credentials)
			throws BadCredentialsException {
		try {
			return moodleService.authenticate(name, credentials);
		} catch (MoodleRequestException e) {
			e.printStackTrace();
			throw new BadCredentialsException("Authentication failed for ["
					+ name + "=" + credentials + "]", e);
		}
	}

	public void setMoodleService(MoodleService moodleService) {
		this.moodleService = moodleService;
	}

}
