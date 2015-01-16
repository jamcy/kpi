package ua.kpi.eec.vml.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "module", schema = "public")
public class Module implements java.io.Serializable {

	private static final long serialVersionUID = 4101240065544284978L;

	private int id;
	private I18n name;
	private String folder;
	private I18n description;
	private I18n pageContent;
	private Room room;
	private int embedWidth;
	private int embedHeight;
	private String embedCode;
	private String imageUrl;
	private Set<Task> tasks = new HashSet<Task>(0);

	public Module() {
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "module_seq_gen")
	@SequenceGenerator(name = "module_seq_gen", sequenceName = "module_id_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "name_i18n_id", nullable = false)
	public I18n getName() {
		return this.name;
	}

	public void setName(I18n name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "description_i18n_id", nullable = false)
	public I18n getDescription() {
		return this.description;
	}

	public void setDescription(I18n description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "page_content_i18n_id", nullable = false)
	public I18n getPageContent() {
		return this.pageContent;
	}

	public void setPageContent(I18n pageContent) {
		this.pageContent = pageContent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "folder", nullable = false, length = 200)
	public String getFolder() {
		return this.folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	@Column(name = "embed_width", nullable = false)
	public int getEmbedWidth() {
		return embedWidth;
	}

	public void setEmbedWidth(int embedWidth) {
		this.embedWidth = embedWidth;
	}

	@Column(name = "embed_height", nullable = false)
	public int getEmbedHeight() {
		return embedHeight;
	}

	public void setEmbedHeight(int embedHeight) {
		this.embedHeight = embedHeight;
	}

	@Column(name = "embed_code", nullable = false)
	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
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
