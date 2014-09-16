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
@Table(name = "tbl_module")
public class Module {
	@Id
	@Column(name="md_id")
	@GeneratedValue
	private long id;
	
	@Column(name="md_embed")
	private String embedCode;
	
	@Column(name="md_short_name")
	private String shortName;
	
	@Column(name="md_picture")
	private String picture;
	
	@Column(name="md_rm_id")
	private long roomId;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="md_name")
	private Translation name;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="md_description")
	private Translation description;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="md_content")
	private Translation content;

	public Module() {
		this.name = new Translation();
		this.content = new Translation();
		this.description = new Translation();
		this.embedCode="";
		this.picture="";
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public Translation getName() {
		return name;
	}

	public void setName(Translation name) {
		this.name = name;
	}

	public Translation getDescription() {
		return description;
	}

	public void setDescription(Translation description) {
		this.description = description;
	}

	public Translation getContent() {
		return content;
	}

	public void setContent(Translation content) {
		this.content = content;
	}

	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	
}
