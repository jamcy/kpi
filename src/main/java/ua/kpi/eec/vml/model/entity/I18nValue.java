package ua.kpi.eec.vml.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "i18n_value", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = {
		"i18n_id", "language_code" }))
public class I18nValue implements java.io.Serializable {

	private static final long serialVersionUID = 1680664449868652714L;

	private int id;
	private LanguageCode languageCode;
	private String content;
	private I18n i18n;

	public I18nValue() {
	}

	public I18nValue(LanguageCode languageCode, String content) {
		this.languageCode = languageCode;
		this.content = content;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "i18n_value_seq_gen")
	@SequenceGenerator(name = "i18n_value_seq_gen", sequenceName = "i18n_value_id_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "language_code", nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	public LanguageCode getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(LanguageCode languageCode) {
		this.languageCode = languageCode;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "i18n_id", nullable = false)
	public I18n getI18n() {
		return i18n;
	}

	public void setI18n(I18n i18n) {
		this.i18n = i18n;
	}

}
