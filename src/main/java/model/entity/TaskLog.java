package model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tbl_task_log")
public class TaskLog {
	
	@Transient
	public static String TS_PROGRESS="progress";
	@Transient
	public static String TS_COMMITED="commited";
	@Transient
	public static String TS_CHECKED="checked";
	@Transient
	public static String TS_REJECTED="rejected";
	
	@Id
	@GeneratedValue
	@Column(name="tlg_id")
	private Long id;
	
	@Column(name="tlg_task_id")
	private Long taskId;
	
	@Column(name="tlg_usr_id")
	private Long userId;
	
	@GeneratedValue
	@Column(name="tlg_updated")
	private Date updated;
	
	@Column(name="tlg_file")
	private String file;
	
	@Column(name="tlg_status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTask() {
		return taskId;
	}

	public void setTask(Long task) {
		this.taskId = task;
	}

	public Long getUser() {
		return userId;
	}

	public void setUser(Long user) {
		this.userId = user;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
