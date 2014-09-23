package ua.kpi.eec.vml.model.form;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.entity.I18n;
import ua.kpi.eec.vml.model.entity.Task;

public class TaskForm implements FormData {

	private String name = "";
	private I18n task;
	private I18n theory;
	private String template = "";

	public TaskForm() {
		this.task = new I18n();
		this.theory = new I18n();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public I18n getTask() {
		return task;
	}

	public void setTask(I18n task) {
		this.task = task;
	}

	public I18n getTheory() {
		return theory;
	}

	public void setTheory(I18n theory) {
		this.theory = theory;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	public boolean validate() {
		boolean result = true;
		// TODO validation
		return result;
	}

	@Override
	public void init(RequestData rd) {
		this.name = rd.getParameter("name");
		this.template = rd.getParameter("template");

//		this.task.setByLanguage("en", rd.getParameter("task_en"));
//		this.task.setByLanguage("uk", rd.getParameter("task_uk"));
//
//		this.theory.setByLanguage("en", rd.getParameter("theory_en"));
//		this.theory.setByLanguage("uk", rd.getParameter("theory_uk"));
	}

	public void load(Task task) {
//		this.name = task.getName();
//		this.theory = task.getTheory();
//		this.task = task.getTask();
//		this.template=task.getTemplate();
	}

	public void update(Task task) {
//		task.setName(this.name);
//		task.getTheory().setByLanguage("en", this.theory.getByLanguage("en"));
//		task.getTheory().setByLanguage("uk", this.theory.getByLanguage("uk"));
//		task.getTask().setByLanguage("en", this.task.getByLanguage("en"));
//		task.getTask().setByLanguage("uk", this.task.getByLanguage("uk"));
//		task.setTemplate(this.template);
	}

}
