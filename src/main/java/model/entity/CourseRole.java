package model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_course_role")
@IdClass(CourseRolePk.class)
public class CourseRole {
	
	@Transient
	public static final String CR_TEACHER = "teacher";
	
	@Transient
	public static final String CR_MODERATOR = "moder";
	
	@Transient
	public static final String CR_STUDENT = "student";
	
	@Transient
	public static final String CR_NOTENROLLED = "nenrol";
	
	@Id
	@Column(name = "usr_id")
	private long userId;

	@Id
	@Column(name = "crs_id")
	private long courseId;

	@Column(name = "role")
	private String role;

	public CourseRole() {
	}
	
	public CourseRole(Long user_id, Long course_id, String role) {
		this.courseId = course_id;
		this.userId = user_id;
		this.role = role;
	}
	
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
