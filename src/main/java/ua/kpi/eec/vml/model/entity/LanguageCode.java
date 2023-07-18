package ua.kpi.eec.vml.model.entity;

public enum LanguageCode {
	UK("uk"), EN("en");

	private String code;

	private LanguageCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
