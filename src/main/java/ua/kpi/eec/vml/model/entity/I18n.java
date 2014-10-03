package ua.kpi.eec.vml.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "i18n", schema = "public")
public class I18n implements java.io.Serializable {

	private static final long serialVersionUID = -327557389933959619L;

	private int id;
	private List<I18nValue> i18nValues = new ArrayList<I18nValue>();

	public I18n() {
	}

	public I18n(List<I18nValue> i18nValues) {
		this.i18nValues = i18nValues;
	}

	public void addStringValue(String stringValue, LanguageCode languageCode) {
		I18nValue i18nValue = new I18nValue(languageCode, stringValue);
		addI18nValue(i18nValue);
	}

	public void addI18nValue(I18nValue value) {

	}

	public String getStringByLanguage(LanguageCode languageCode) {
		for (I18nValue i18n : i18nValues) {
			if (i18n.getLanguageCode().equals(languageCode)) {
				return i18n.getContent();
			}
		}
		return null;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "i18n_seq_gen")
	@SequenceGenerator(name = "i18n_seq_gen", sequenceName = "i18n_id_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "i18n", cascade = CascadeType.ALL)
	public List<I18nValue> getI18nValues() {
		return this.i18nValues;
	}

	public void setI18nValues(List<I18nValue> i18nValues) {
		this.i18nValues = i18nValues;
	}
}
