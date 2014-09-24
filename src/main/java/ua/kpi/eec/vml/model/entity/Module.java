package ua.kpi.eec.vml.model.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "module", schema = "public")
public class Module implements java.io.Serializable {

	private static final long serialVersionUID = 4101240065544284978L;

	private int id;
	private I18n i18nByNameI18nId;
	private I18n i18nByDescriptionI18nId;
	private I18n i18nByPageContentI18nId;
	private Room room;
	private String code;
	private String embed;
	private String imageUrl;
	private Set<Task> tasks = new HashSet<Task>(0);

	public Module() {
	}

	public Module(int id, I18n i18nByNameI18nId, I18n i18nByDescriptionI18nId,
			I18n i18nByPageContentI18nId, Room room, String code, String embed,
			String imageUrl) {
		this.id = id;
		this.i18nByNameI18nId = i18nByNameI18nId;
		this.i18nByDescriptionI18nId = i18nByDescriptionI18nId;
		this.i18nByPageContentI18nId = i18nByPageContentI18nId;
		this.room = room;
		this.code = code;
		this.embed = embed;
		this.imageUrl = imageUrl;
	}

	public Module(int id, I18n i18nByNameI18nId, I18n i18nByDescriptionI18nId,
			I18n i18nByPageContentI18nId, Room room, String code, String embed,
			String imageUrl, Set<Task> tasks) {
		this.id = id;
		this.i18nByNameI18nId = i18nByNameI18nId;
		this.i18nByDescriptionI18nId = i18nByDescriptionI18nId;
		this.i18nByPageContentI18nId = i18nByPageContentI18nId;
		this.room = room;
		this.code = code;
		this.embed = embed;
		this.imageUrl = imageUrl;
		this.tasks = tasks;
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
	@JoinColumn(name = "description_i18n_id", nullable = false)
	public I18n getI18nByDescriptionI18nId() {
		return this.i18nByDescriptionI18nId;
	}

	public void setI18nByDescriptionI18nId(I18n i18nByDescriptionI18nId) {
		this.i18nByDescriptionI18nId = i18nByDescriptionI18nId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_content_i18n_id", nullable = false)
	public I18n getI18nByPageContentI18nId() {
		return this.i18nByPageContentI18nId;
	}

	public void setI18nByPageContentI18nId(I18n i18nByPageContentI18nId) {
		this.i18nByPageContentI18nId = i18nByPageContentI18nId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "code", nullable = false, length = 200)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "embed", nullable = false)
	public String getEmbed() {
		return this.embed;
	}

	public void setEmbed(String embed) {
		this.embed = embed;
	}

	@Column(name = "image_url", nullable = false, length = 200)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
