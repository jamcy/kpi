package model.entity;

import java.io.Serializable;

public class CourseRolePk implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private long courseId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

}
