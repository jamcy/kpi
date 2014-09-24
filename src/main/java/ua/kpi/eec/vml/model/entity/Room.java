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
@Table(name = "room", schema = "public")
public class Room implements java.io.Serializable {

	private static final long serialVersionUID = 3765804669205960914L;

	private long id;
	private I18n i18n;
	private String imageUrl;
	private Set<Module> modules = new HashSet<Module>(0);

	public Room() {
	}

	public Room(long id, I18n i18n, String imageUrl) {
		this.id = id;
		this.i18n = i18n;
		this.imageUrl = imageUrl;
	}

	public Room(long id, I18n i18n, String imageUrl, Set<Module> modules) {
		this.id = id;
		this.i18n = i18n;
		this.imageUrl = imageUrl;
		this.modules = modules;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "name_i18n_id", nullable = false)
	public I18n getI18n() {
		return this.i18n;
	}

	public void setI18n(I18n i18n) {
		this.i18n = i18n;
	}

	@Column(name = "image_url", nullable = false, length = 200)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	public Set<Module> getModules() {
		return this.modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

}
