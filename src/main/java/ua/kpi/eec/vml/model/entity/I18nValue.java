package ua.kpi.eec.vml.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "i18n_value", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = {
		"i18n_id", "language_code" }))
public class I18nValue implements java.io.Serializable {

	private static final long serialVersionUID = 1680664449868652714L;

	private int id;
	private I18n i18n;
	private String languageCode;
	private String content;

	public I18nValue() {
	}

	public I18nValue(int id, I18n i18n, String languageCode, String content) {
		this.id = id;
		this.i18n = i18n;
		this.languageCode = languageCode;
		this.content = content;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "i18n_id", nullable = false)
	public I18n getI18n() {
		return this.i18n;
	}

	public void setI18n(I18n i18n) {
		this.i18n = i18n;
	}

	@Column(name = "language_code", nullable = false, length = 2)
	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
