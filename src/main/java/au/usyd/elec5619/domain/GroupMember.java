package au.usyd.elec5619.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GroupMember")
public class GroupMember {
	@Id
	@GeneratedValue
	@Column(name="GroupMemberId", unique=true)
	private long id;

	@Column(name="GroupId")
	private long groupId;
	
	@Column(name="Member")
	private String member;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(long id) {
		this.groupId = id;
	}
	
	public String getMemberId() {
		return this.member;
	}
	
	public void setMemberId(String username) {
		this.member = username;
	}
}
