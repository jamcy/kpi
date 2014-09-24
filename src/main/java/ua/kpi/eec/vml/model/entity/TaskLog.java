package ua.kpi.eec.vml.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "task_log", schema = "public")
public class TaskLog implements java.io.Serializable {

	private static final long serialVersionUID = 324842966814053205L;
	
	private int id;
	private Account account;
	private Task task;
	private Date lastUpdated;
	private String status;
	private String code;

	public TaskLog() {
	}

	public TaskLog(int id, Account account, Task task, String code) {
		this.id = id;
		this.account = account;
		this.task = task;
		this.code = code;
	}

	public TaskLog(int id, Account account, Task task, Date lastUpdated,
			String status, String code) {
		this.id = id;
		this.account = account;
		this.task = task;
		this.lastUpdated = lastUpdated;
		this.status = status;
		this.code = code;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", length = 29)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "status", length = 200)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "code", nullable = false, length = 200)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
