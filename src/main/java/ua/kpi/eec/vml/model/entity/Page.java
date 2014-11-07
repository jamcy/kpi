package ua.kpi.eec.vml.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "page", schema = "public")
public class Page implements java.io.Serializable {

	private static final long serialVersionUID = 3752039564906723751L;

	private int id;
	private I18n name;
	private I18n content;
	private String urlSuffix;

	public Page() {
	}

	public Page(int id, I18n name, I18n content,
			String urlSuffix) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.urlSuffix = urlSuffix;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "page_seq_gen")
	@SequenceGenerator(name = "page_seq_gen", sequenceName = "page_id_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "name_i18n_id", nullable = false)
	public I18n getName() {
		return this.name;
	}

	public void setName(I18n name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "content_i18n_id", nullable = false)
	public I18n getContent() {
		return this.content;
	}

	public void setContent(I18n content) {
		this.content = content;
	}

	@Column(name = "url_suffix", nullable = false, length = 200)
	public String getUrlSuffix() {
		return this.urlSuffix;
	}

	public void setUrlSuffix(String urlSuffix) {
		this.urlSuffix = urlSuffix;
	}

}
