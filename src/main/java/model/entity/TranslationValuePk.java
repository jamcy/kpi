package model.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TranslationValuePk implements Serializable {

	private Long translationId;
	private String language;

	public Long getTranslationId() {
		return translationId;
	}

	public void setTranslationId(Long translationId) {
		this.translationId = translationId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
