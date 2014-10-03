package ua.kpi.eec.vml.model.entity;

public enum LanguageCode {
	UK("ua"), EN("en");

	private String languageCode;

	private LanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String languageCode() {
		return languageCode;
	}
}
