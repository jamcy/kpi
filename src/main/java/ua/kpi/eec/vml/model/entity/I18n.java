package ua.kpi.eec.vml.model.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "i18n", schema = "public")
public class I18n implements java.io.Serializable {

	private static final long serialVersionUID = -327557389933959619L;

	private int id;
	private Set<I18nValue> i18nValues = new HashSet<I18nValue>(0);

	public I18n() {
	}

	public I18n(int id) {
		this.id = id;
	}

	public I18n(int id, Set<I18nValue> i18nValues) {
		this.id = id;
		this.i18nValues = i18nValues;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "i18n")
	public Set<I18nValue> getI18nValues() {
		return this.i18nValues;
	}

	public void setI18nValues(Set<I18nValue> i18nValues) {
		this.i18nValues = i18nValues;
	}
}
