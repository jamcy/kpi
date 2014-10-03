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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "course", schema = "public")
public class Course implements java.io.Serializable {

	private static final long serialVersionUID = -4824245796884766335L;

	private int id;
	private I18n i18n;
	private int nameI18nId;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<CourseRole> courseRoles = new HashSet<CourseRole>(0);

	public Course() {
	}

	public Course(int id, I18n i18n, int nameI18nId) {
		this.id = id;
		this.i18n = i18n;
		this.nameI18nId = nameI18nId;
	}

	public Course(int id, I18n i18n, int nameI18nId, Set<Task> tasks, Set<CourseRole> courseRoles) {
		this.id = id;
		this.i18n = i18n;
		this.nameI18nId = nameI18nId;
		this.tasks = tasks;
		this.courseRoles = courseRoles;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "description_i18n_id", nullable = false)
	public I18n getI18n() {
		return this.i18n;
	}

	public void setI18n(I18n i18n) {
		this.i18n = i18n;
	}

	@Column(name = "name_i18n_id", nullable = false)
	public int getNameI18nId() {
		return this.nameI18nId;
	}

	public void setNameI18nId(int nameI18nId) {
		this.nameI18nId = nameI18nId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	public Set<CourseRole> getCourseRoles() {
		return this.courseRoles;
	}

	public void setCourseRoles(Set<CourseRole> courseRoles) {
		this.courseRoles = courseRoles;
	}

}
