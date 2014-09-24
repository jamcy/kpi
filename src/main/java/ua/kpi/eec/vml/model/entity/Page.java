package ua.kpi.eec.vml.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "page", schema = "public")
public class Page implements java.io.Serializable {

	private static final long serialVersionUID = 3752039564906723751L;

	private int id;
	private I18n i18nByNameI18nId;
	private I18n i18nByContentI18nId;
	private String urlSuffix;

	public Page() {
	}

	public Page(int id, I18n i18nByNameI18nId, I18n i18nByContentI18nId,
			String urlSuffix) {
		this.id = id;
		this.i18nByNameI18nId = i18nByNameI18nId;
		this.i18nByContentI18nId = i18nByContentI18nId;
		this.urlSuffix = urlSuffix;
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
	@JoinColumn(name = "name_i18n_id", nullable = false)
	public I18n getI18nByNameI18nId() {
		return this.i18nByNameI18nId;
	}

	public void setI18nByNameI18nId(I18n i18nByNameI18nId) {
		this.i18nByNameI18nId = i18nByNameI18nId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_i18n_id", nullable = false)
	public I18n getI18nByContentI18nId() {
		return this.i18nByContentI18nId;
	}

	public void setI18nByContentI18nId(I18n i18nByContentI18nId) {
		this.i18nByContentI18nId = i18nByContentI18nId;
	}

	@Column(name = "url_suffix", nullable = false, length = 200)
	public String getUrlSuffix() {
		return this.urlSuffix;
	}

	public void setUrlSuffix(String urlSuffix) {
		this.urlSuffix = urlSuffix;
	}

}
