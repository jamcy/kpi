package ua.kpi.eec.vml.model.dto;

import java.io.Serializable;

public class MoodleTokenResponse implements Serializable {
    private static final long serialVersionUID = -8122295244606339202L;
    private String token;
    private String error;

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getError() {
	return error;
    }

    public void setError(String error) {
	this.error = error;
    }
}
