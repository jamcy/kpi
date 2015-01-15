package ua.kpi.eec.vml.model.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import ua.kpi.eec.vml.service.validation.constraint.ModuleName;

@SuppressWarnings("serial")
public class ModuleForm implements Serializable {
	
	private int id;
	@ModuleName
	private String shortName;
	@NotBlank
	private String nameEn;
	@NotBlank
	private String nameUk;
	private String descriptionEn;
	private String descriptionUk;
	private String contentEn;
	private String contentUk;
	@NotNull
	private Long roomId;
	private int embedWidth;
	private int embedHeight;
	private String embedCode;
	private String imageUrl;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionUk() {
		return descriptionUk;
	}

	public void setDescriptionUk(String descriptionUk) {
		this.descriptionUk = descriptionUk;
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

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	public int getEmbedWidth() {
		return embedWidth;
	}

	public void setEmbedWidth(int embedWidth) {
		this.embedWidth = embedWidth;
	}

	public int getEmbedHeight() {
		return embedHeight;
	}

	public void setEmbedHeight(int embedHeight) {
		this.embedHeight = embedHeight;
	}

	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
