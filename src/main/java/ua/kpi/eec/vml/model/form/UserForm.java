package ua.kpi.eec.vml.model.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.entity.Account;

public class UserForm implements FormData {

	private Map<Long, String> courseRoles;
	private String systemRole;

	// CONSTRUCTOR
	public UserForm() {
		courseRoles = new HashMap<Long, String>();
	}

	// GETTERS & SETTERS

	public String getSystemRole() {
		return systemRole;
	}

	public Map<Long, String> getCourseRoles() {
		return courseRoles;
	}

	public void setCourseRoles(Map<Long, String> courseRoles) {
		this.courseRoles = courseRoles;
	}

	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}

	// INTERFACE IMPLEMENTATION
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void init(RequestData rd) {
		this.systemRole = rd.getParameter("system_role");
		List<String> params = rd.getParameterNames();
		for (String param : params) {
			if (param.matches("role_[0-9]+")) {
				this.courseRoles.put(Long.parseLong(param.substring(5)),
						rd.getParameter(param));
			}
		}
	}

	// METODS
	public void load(Account user) {
		this.systemRole = user.getRole();
//		for (CourseRole role : user.getCourseRoles()) {
//			this.courseRoles.put(role.getCourseId(), role.getRole());
//		}
	}

	public void update(Account user) {
		user.setRole(this.systemRole);
//		Set<Long> courseids = this.courseRoles.keySet();
//		for(Long courseid: courseids) {
//			user.setRole(courseid, this.courseRoles.get(courseid));
//		}
	}

}
