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

	public String getContentByLanguage(LanguageCode languageCode) {
		for (I18nValue value : i18nValues) {
			if (languageCode.equals(value.getLanguageCode()))
				return value.getContent();
		}
		return null;
	}

	public void setContentByLanguage(LanguageCode languageCode, String content) {
		if (getContentByLanguage(languageCode) == null) {
			I18nValue value = new I18nValue(languageCode, content);
			value.setI18n(this);
			i18nValues.add(value);
			return;
		}
		for (I18nValue value : i18nValues) {
			if (languageCode.equals(value.getLanguageCode()))
				value.setContent(content);
		}
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "i18n", cascade = CascadeType.ALL)
	public List<I18nValue> getI18nValues() {
		return this.i18nValues;
	}

	public void setI18nValues(List<I18nValue> i18nValues) {
		this.i18nValues = i18nValues;
	}
}
