package ua.kpi.eec.vml.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "account", schema = "public", uniqueConstraints = {
		@UniqueConstraint(columnNames = "moodle_id"),
		@UniqueConstraint(columnNames = "email") })
public class Account implements java.io.Serializable {

	private long id;
	private int moodleId;
	private String email;
	private String fullName;
	private String role;
	private Set<TaskLog> taskLogs = new HashSet<TaskLog>(0);
	private Set<CourseRole> courseRoles = new HashSet<CourseRole>(0);

	public Account() {
	}

	public Account(long id, int moodleId, String email, String fullName,
			String role) {
		this.id = id;
		this.moodleId = moodleId;
		this.email = email;
		this.fullName = fullName;
		this.role = role;
	}

	public Account(long id, int moodleId, String email, String fullName,
			String role, Set<TaskLog> taskLogs, Set<CourseRole> courseRoles) {
		this.id = id;
		this.moodleId = moodleId;
		this.email = email;
		this.fullName = fullName;
		this.role = role;
		this.taskLogs = taskLogs;
		this.courseRoles = courseRoles;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "moodle_id", unique = true, nullable = false)
	public int getMoodleId() {
		return this.moodleId;
	}

	public void setMoodleId(int moodleId) {
		this.moodleId = moodleId;
	}

	@Column(name = "email", unique = true, nullable = false, length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "full_name", nullable = false, length = 200)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "role", nullable = false, length = 200)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<TaskLog> getTaskLogs() {
		return this.taskLogs;
	}

	public void setTaskLogs(Set<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<CourseRole> getCourseRoles() {
		return this.courseRoles;
	}

	public void setCourseRoles(Set<CourseRole> courseRoles) {
		this.courseRoles = courseRoles;
	}

}
