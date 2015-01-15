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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "room", schema = "public")
public class Room implements java.io.Serializable {
	
	private long id;
	private I18n name;
	private I18n description;
	private String imageUrl;
	private Set<Module> modules = new HashSet<Module>(0);

	public Room() {
	}

	public Room(I18n i18n, String imageUrl) {
		this.name = i18n;
		this.imageUrl = imageUrl;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq_gen")
	@SequenceGenerator(name = "room_seq_gen", sequenceName = "room_id_seq")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "name_i18n_id", nullable = false)
	public I18n getName() {
		return this.name;
	}

	public void setName(I18n i18n) {
		this.name = i18n;
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "description_i18n_id", nullable = false)
	public I18n getDescription() {
		return description;
	}

	public void setDescription(I18n description) {
		this.description = description;
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
