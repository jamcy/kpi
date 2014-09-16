package model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_room")
public class Room {

	@Id
	@Column(name = "rm_id")
	@GeneratedValue
	private long id;

	@Column(name = "rm_picture")
	private String rmPicture;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="rm_name")
	private Translation name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRmPicture() {
		return rmPicture;
	}

	public void setRmPicture(String rmPicture) {
		this.rmPicture = rmPicture;
	}

	public Translation getName() {
		return name;
	}

	public void setName(Translation name) {
		this.name = name;
	}

}
