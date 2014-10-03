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
@Table(name = "task", schema = "public")
public class Task implements java.io.Serializable {

	private static final long serialVersionUID = -3626590479219643229L;

	private int id;
	private Course course;
	private Module module;
	private I18n i18nByContentI18nId;
	private I18n i18nByDefinitionI18nId;
	private int moodleId;
	private int nameI18nId;
	private String startingTemplateUrl;
	private Set<TaskLog> taskLogs = new HashSet<TaskLog>(0);

	public Task() {
	}

	public Task(int id, Course course, Module module, I18n i18nByContentI18nId,
			I18n i18nByDefinitionI18nId, int moodleId, int nameI18nId) {
		this.id = id;
		this.course = course;
		this.module = module;
		this.i18nByContentI18nId = i18nByContentI18nId;
		this.i18nByDefinitionI18nId = i18nByDefinitionI18nId;
		this.moodleId = moodleId;
		this.nameI18nId = nameI18nId;
	}

	public Task(int id, Course course, Module module, I18n i18nByContentI18nId,
			I18n i18nByDefinitionI18nId, int moodleId, int nameI18nId,
			String startingTemplateUrl, Set<TaskLog> taskLogs) {
		this.id = id;
		this.course = course;
		this.module = module;
		this.i18nByContentI18nId = i18nByContentI18nId;
		this.i18nByDefinitionI18nId = i18nByDefinitionI18nId;
		this.moodleId = moodleId;
		this.nameI18nId = nameI18nId;
		this.startingTemplateUrl = startingTemplateUrl;
		this.taskLogs = taskLogs;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq_gen")
	@SequenceGenerator(name = "task_seq_gen", sequenceName = "task_id_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id", nullable = false)
	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_i18n_id", nullable = false)
	public I18n getI18nByContentI18nId() {
		return this.i18nByContentI18nId;
	}

	public void setI18nByContentI18nId(I18n i18nByContentI18nId) {
		this.i18nByContentI18nId = i18nByContentI18nId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "definition_i18n_id", nullable = false)
	public I18n getI18nByDefinitionI18nId() {
		return this.i18nByDefinitionI18nId;
	}

	public void setI18nByDefinitionI18nId(I18n i18nByDefinitionI18nId) {
		this.i18nByDefinitionI18nId = i18nByDefinitionI18nId;
	}

	@Column(name = "moodle_id", nullable = false)
	public int getMoodleId() {
		return this.moodleId;
	}

	public void setMoodleId(int moodleId) {
		this.moodleId = moodleId;
	}

	@Column(name = "name_i18n_id", nullable = false)
	public int getNameI18nId() {
		return this.nameI18nId;
	}

	public void setNameI18nId(int nameI18nId) {
		this.nameI18nId = nameI18nId;
	}

	@Column(name = "starting_template_url", length = 200)
	public String getStartingTemplateUrl() {
		return this.startingTemplateUrl;
	}

	public void setStartingTemplateUrl(String startingTemplateUrl) {
		this.startingTemplateUrl = startingTemplateUrl;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	public Set<TaskLog> getTaskLogs() {
		return this.taskLogs;
	}

	public void setTaskLogs(Set<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

}
