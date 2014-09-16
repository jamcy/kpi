package model.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_course")
public class Course {

	public Course() {
		this.name = new Translation();
		this.description = new Translation();
	}
	
	@Id
	@Column(name="crs_mdl_id")
	private long id;
	
	/*@Column(name="crs_name")
	@GeneratedValue
	private long nameTranslationId;
	
	
	@Column(name="crs_description")
	@GeneratedValue
	private long descriptionTranslationId;*/
	

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="crs_name")
	private Translation name;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="crs_description")
	private Translation description;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	/*public long getNameTranslationId() {
		return nameTranslationId;
	}

	public void setNameTranslationId(long nameTranslationId) {
		this.nameTranslationId = nameTranslationId;
	}

	public long getDescriptionTranslationId() {
		return descriptionTranslationId;
	}

	public void setDescriptionTranslationId(long descriptionTranslationId) {
		this.descriptionTranslationId = descriptionTranslationId;
	}*/

}
