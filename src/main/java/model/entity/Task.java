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
@Table(name = "tbl_task")
public class Task {

	@Id
	@Column(name = "tsk_mdl_id")
	private long id;

	@Column(name = "tsk_name")
	private String name;

	/*@Column(name = "tsk_task")
	private long taskTranslationId;

	@Column(name = "tsk_theory")
	@GeneratedValue
	private long theoryTranslationId;
*/
	@Column(name = "tsk_course")
	@GeneratedValue
	private long courseId;

	@Column(name = "tsk_module")
	private long moduleId;
	
	@Column(name="tsk_template")
	private String template;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="tsk_task")
	private Translation task;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="tsk_theory")
	private Translation theory;

	public Task() {
		this.task = new Translation();
		this.theory = new Translation();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public long getTaskTranslationId() {
		return taskTranslationId;
	}

	public void setTaskTranslationId(long taskTranslationId) {
		this.taskTranslationId = taskTranslationId;
	}

	public long getTheoryTranslationId() {
		return theoryTranslationId;
	}

	public void setTheoryTranslationId(long theoryTranslationId) {
		this.theoryTranslationId = theoryTranslationId;
	}
*/
	public Translation getTask() {
		return task;
	}

	public void setTask(Translation task) {
		this.task = task;
	}

	public Translation getTheory() {
		return theory;
	}

	public void setTheory(Translation theory) {
		this.theory = theory;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
