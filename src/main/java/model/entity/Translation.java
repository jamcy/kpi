package model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_translation_id")
public class Translation {

	public Translation() {
		this.translationValues = new HashSet<TranslationValue>();
	}

	@Id
	@Column(name = "tr_id")
	@GeneratedValue
	private long id;

	@OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<TranslationValue> translationValues;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<TranslationValue> getTranslationValues() {
		return translationValues;
	}

	public void setTranslationValues(Set<TranslationValue> translationValues) {
		this.translationValues = translationValues;
	}

	public String getByLanguage(String lang) {
		if (this.translationValues == null)
			return "";
		for (TranslationValue tv : this.translationValues) {
			if (tv.getLanguage().equals(lang)) {
				return tv.getValue();
			}
		}
		return "";
	}

	public void setByLanguage(String lang, String value) {
		for (TranslationValue tv : this.translationValues) {
			if (tv.getLanguage().equals(lang)) {
				tv.setValue(value);
				return;
			}
		}
		this.translationValues.add(new TranslationValue(this, lang, value));
	}
	
	public String toString(String lang) {
		if (this.translationValues == null)
			return "";
		for (TranslationValue tv : this.translationValues) {
			if (tv.getLanguage().equals(lang)) {
				return tv.getValue();
			}
		}
		for(TranslationValue tv: this.translationValues) {
			if(!tv.getValue().equals("")) {
				return tv.getValue();
			}
		}
		return "";
	}

}
