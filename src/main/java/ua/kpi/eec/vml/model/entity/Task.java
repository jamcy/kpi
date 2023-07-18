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
	private I18n content;
	private I18n definition;
	private int moodleId;
	private int name;
	private String startingTemplateUrl;
	private Set<TaskLog> taskLogs = new HashSet<TaskLog>(0);

	public Task() {
	}

	public Task(int id, Course course, Module module, I18n content,
			I18n definition, int moodleId, int name) {
		this.id = id;
		this.course = course;
		this.module = module;
		this.content = content;
		this.definition = definition;
		this.moodleId = moodleId;
		this.name = name;
	}

	public Task(int id, Course course, Module module, I18n content,
			I18n definition, int moodleId, int name,
			String startingTemplateUrl, Set<TaskLog> taskLogs) {
		this.id = id;
		this.course = course;
		this.module = module;
		this.content = content;
		this.definition = definition;
		this.moodleId = moodleId;
		this.name = name;
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
	public I18n getcontent() {
		return this.content;
	}

	public void setcontent(I18n content) {
		this.content = content;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "definition_i18n_id", nullable = false)
	public I18n getdefinition() {
		return this.definition;
	}

	public void setdefinition(I18n definition) {
		this.definition = definition;
	}

	@Column(name = "moodle_id", nullable = false)
	public int getMoodleId() {
		return this.moodleId;
	}

	public void setMoodleId(int moodleId) {
		this.moodleId = moodleId;
	}

	@Column(name = "name_i18n_id", nullable = false)
	public int getname() {
		return this.name;
	}

	public void setname(int name) {
		this.name = name;
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
