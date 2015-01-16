package ua.kpi.eec.vml.model.form;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class ModuleForm implements Serializable {
	private Integer id;
	@NotBlank(message="Module folder name should not be blank")
	//TODO: check if folder name already used by another module
	private String folder;
	private String imageUrl;
	@NotNull(message="Room should be selected")
	private Long roomId;
	@NotBlank(message="Module name should not be blank")
	private String nameEn;
	@NotBlank(message="Module name should not be blank")
	private String nameUk;
	@NotBlank(message="Module description should not be blank")
	private String descriptionEn;
	@NotBlank(message="Module description should not be blank")
	private String descriptionUk;
	private String contentEn;
	private String contentUk;
	@NotNull(message="Embed size should not be blank")
	@Min(value=1, message="Embed size should be positive integer")
	private Integer embedWidth;
	@NotNull(message="Embed size should not be blank")
	@Min(value=1, message="Embed size should be positive integer")
	private Integer embedHeight;
	@NotBlank(message="Module embed code should not be blank")
	private String embedCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
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
	
	public Integer getEmbedWidth() {
		return embedWidth;
	}

	public void setEmbedWidth(Integer embedWidth) {
		this.embedWidth = embedWidth;
	}

	public Integer getEmbedHeight() {
		return embedHeight;
	}

	public void setEmbedHeight(Integer embedHeight) {
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

}
