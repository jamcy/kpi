package model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_user")
public class User {

	@Transient
	public static final String SR_ADMIN = "admin";

	@Transient
	public static final String SR_MODERATOR = "moder";

	@Transient
	public static final String SR_USER = "user";

	@Transient
	public static final String SR_SUPERADMIN = "sadmin";

	@Id
	@Column(name = "usr_mdl_id")
	private long moodleId;

	@Column(name = "usr_username")
	private String username;

	@Column(name = "usr_language", length = 2)
	private String language;

	@Column(name = "usr_first_name")
	private String firstName;

	@Column(name = "usr_last_name")
	private String lastName;

	@Column(name = "usr_system_role")
	private String role;

	@OneToMany(mappedBy = "userId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CourseRole> courseRoles;

	public User() {
		this.role = "user";
		this.courseRoles = new HashSet<CourseRole>();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getMoodleId() {
		return moodleId;
	}

	public void setMoodleId(long moodleId) {
		this.moodleId = moodleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<CourseRole> getCourseRoles() {
		return courseRoles;
	}

	public void setCourseRoles(Set<CourseRole> courseRoles) {
		this.courseRoles = courseRoles;
	}

	public void setRole(Long courseId, String role) {
		CourseRole crs = null;
		for (CourseRole cr : courseRoles) {
			if (cr.getCourseId() == courseId) {
				crs = cr;
				break;
			}
		}
		if (role.equals(CourseRole.CR_NOTENROLLED)) {
			if (crs != null) {
				courseRoles.remove(crs);
			}
		} else {
			if (crs == null) {
				courseRoles.add(new CourseRole(this.moodleId, courseId, role));
			} else {
				crs.setRole(role);
			}
		}

	}

	public String getRole(Long courseId) {
		for (CourseRole cr : courseRoles) {
			if (cr.getCourseId() == courseId) {
				return cr.getRole();
			}
		}
		return CourseRole.CR_NOTENROLLED;
	}

}
