package model.entity;

import java.io.Serializable;

public class TranslationValuePk implements Serializable {

	private static final long serialVersionUID = 1L;

	private Translation id;
	private String language;
	

	public Translation getId() {
		return id;
	}

	public void setId(Translation id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
