package ua.kpi.eec.vml.model.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class PageForm implements Serializable {
	
	private Integer id;
	@NotBlank(message="Page url suffix should not be blank")
	//TODO check if page url suffix unique
	private String suffix;
	@NotBlank(message="Page name should not be blank")
	private String nameEn;
	@NotBlank(message="Page name should not be blank")
	private String nameUk;
	private String contentEn;
	private String contentUk;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameUk() {
		return nameUk;
	}

	public void setNameUk(String nameUk) {
		this.nameUk = nameUk;
	}

	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}

	public String getContentUk() {
		return contentUk;
	}

	public void setContentUk(String contentUk) {
		this.contentUk = contentUk;
	}

}
