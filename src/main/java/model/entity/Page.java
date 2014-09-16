package model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_page")

public class Page {
	
	@Id
	@Column(name="pg_id")
	@GeneratedValue
	private long id;
	
/*	@Column(name="pg_name")
	@GeneratedValue
	private long nameTranslationId;
	
	@Column(name="pg_content")
	@GeneratedValue
	private long contentTranslationId;*/
	
	@Column(name="pg_url")
	private String urlSuffix;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="pg_name")
	private Translation name;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="pg_content")
	private Translation content;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*public long getNameTranslationId() {
		return nameTranslationId;
	}

	public void setNameTranslationId(long nameTranslationId) {
		this.nameTranslationId = nameTranslationId;
	}

	public long getContentTranslationId() {
		return contentTranslationId;
	}

	public void setContentTranslationId(long contentTranslationId) {
		this.contentTranslationId = contentTranslationId;
	}*/

	public String getUrlSuffix() {
		return urlSuffix;
	}

	public void setUrlSuffix(String urlSuffix) {
		this.urlSuffix = urlSuffix;
	}

	public Translation getName() {
		return name;
	}

	public void setName(Translation name) {
		this.name = name;
	}

	public Translation getContent() {
		return content;
	}

	public void setContent(Translation content) {
		this.content = content;
	}
	
	
	
}
