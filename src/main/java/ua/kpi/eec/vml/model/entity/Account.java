package ua.kpi.eec.vml.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(name = "account", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "moodle_id"),
		@UniqueConstraint(columnNames = "username") })
public class Account implements java.io.Serializable {

	private long id;
	private int moodleId;
	private String username;
	private String fullName;
	private SystemRole role;
	private Set<TaskLog> taskLogs = new HashSet<TaskLog>(0);
	private Set<Course> courses = new HashSet<Course>(0);

	public Account() {
	}

	public Account(long id, int moodleId, String username, String fullName, SystemRole role) {
		this.id = id;
		this.moodleId = moodleId;
		this.username = username;
		this.fullName = fullName;
		this.role = role;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq_gen")
	@SequenceGenerator(name = "account_seq_gen", sequenceName = "account_id_seq")
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

	@Column(name = "username", unique = true, nullable = false, length = 200)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "full_name", nullable = false, length = 200)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "role", nullable = false, length = 200)
	@Enumerated(EnumType.STRING)
	public SystemRole getRole() {
		return this.role;
	}

	public void setRole(SystemRole role) {
		this.role = role;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<TaskLog> getTaskLogs() {
		return this.taskLogs;
	}

	public void setTaskLogs(Set<TaskLog> taskLogs) {
		this.taskLogs = taskLogs;
	}

	// TODO add cascades
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_course", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
