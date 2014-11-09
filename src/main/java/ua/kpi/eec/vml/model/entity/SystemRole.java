package ua.kpi.eec.vml.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum SystemRole implements GrantedAuthority {
	GUEST, USER, MODERATOR, TEACHER, ADMIN;

	@Override
	public String getAuthority() {
		return "ROLE_" + this;
	}
}
