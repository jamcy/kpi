package model.form;

import common.RequestData;
import model.entity.Task;
import model.entity.Translation;

public class TaskForm implements FormData {

	private String name = "";
	private Translation task;
	private Translation theory;
	private String template = "";

	public TaskForm() {
		this.task = new Translation();
		this.theory = new Translation();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

		this.task.setByLanguage("en", rd.getParameter("task_en"));
		this.task.setByLanguage("uk", rd.getParameter("task_uk"));

		this.theory.setByLanguage("en", rd.getParameter("theory_en"));
		this.theory.setByLanguage("uk", rd.getParameter("theory_uk"));
	}

	public void load(Task task) {
		this.name = task.getName();
		this.theory = task.getTheory();
		this.task = task.getTask();
		this.template=task.getTemplate();
	}

	public void update(Task task) {
		task.setName(this.name);
		task.getTheory().setByLanguage("en", this.theory.getByLanguage("en"));
		task.getTheory().setByLanguage("uk", this.theory.getByLanguage("uk"));
		task.getTask().setByLanguage("en", this.task.getByLanguage("en"));
		task.getTask().setByLanguage("uk", this.task.getByLanguage("uk"));
		task.setTemplate(this.template);
	}

}
