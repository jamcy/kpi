package ua.kpi.eec.vml.model.dto;

import java.io.Serializable;

import ua.kpi.eec.vml.model.entity.SystemRole;

@SuppressWarnings("serial")
public class AccountData implements Serializable {
	
	private int moodleId;
	private String username;
	private String fullName;
	private SystemRole role;

	public int getMoodleId() {
		return moodleId;
	}

	public void setMoodleId(int moodleId) {
		this.moodleId = moodleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public SystemRole getRole() {
		return role;
	}

	public void setRole(SystemRole role) {
		this.role = role;
	}
}
