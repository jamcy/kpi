package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_translation_value")
@IdClass(TranslationValuePk.class)
public class TranslationValue {

	public TranslationValue() {
		this(new Translation(), null, null);
	}

	public TranslationValue(Translation id, String language, String value) {
		this.id = id;
		this.language = language;
		this.value = value;
	}

	public TranslationValue(String language, String value) {
		this(new Translation(), language, value);
	}

	@Id
	@ManyToOne
    @JoinColumn(name="tr_id")
	private Translation id;

	@Id
	@Column(name = "tr_language_code")
	private String language;

	@Column(name = "tr_value")
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
