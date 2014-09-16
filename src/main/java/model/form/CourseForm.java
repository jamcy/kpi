package model.form;

import model.entity.Course;
import model.entity.Translation;
import common.RequestData;

public class CourseForm implements FormData {

	private String action;
	private Long id;
	private Translation name;
	private Translation description;

	public CourseForm() {
		this.id = new Long(0);
		this.name = new Translation();
		this.description = new Translation();
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void init(RequestData rd) {
		this.id = Long.parseLong(rd.getParameter("id").trim());
		this.name = new Translation();
		this.name.setByLanguage("uk", rd.getParameter("name_uk"));
		this.name.setByLanguage("en", rd.getParameter("name_en"));
		this.description = new Translation();
		this.description.setByLanguage("en", rd.getParameter("description_en"));
		this.description.setByLanguage("uk", rd.getParameter("description_uk"));
	}

	// Updates specific course instance with data it holds.
	public void update(Course course) {
		course.setId(this.id);
		course.setName(this.name);
		course.setDescription(this.description);
	}

	// Loads course instance data into itself.
	public void load(Course course) {
		this.description = course.getDescription();
		this.name = course.getName();
		this.id = course.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
