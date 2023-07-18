package ua.kpi.eec.vml.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "course", schema = "public")
public class Course implements java.io.Serializable {

	private static final long serialVersionUID = -4824245796884766335L;

	private int id;
	private I18n name;
	private I18n description;
	private String imageUrl;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<Account> students = new HashSet<Account>(0);

	public Course() {
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq_gen")
	@SequenceGenerator(name = "course_seq_gen", sequenceName = "course_id_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "description_i18n_id", nullable = false)
	public I18n getDescription() {
		return this.description;
	}

	public void setDescription(I18n description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "name_i18n_id", nullable = false)
	public I18n getName() {
		return this.name;
	}

	public void setName(I18n name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "account_course", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
	public Set<Account> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Account> students) {
		this.students = students;
	}

	@Column(name = "image_url", nullable = true)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
