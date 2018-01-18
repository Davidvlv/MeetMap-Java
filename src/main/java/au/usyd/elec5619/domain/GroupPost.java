package au.usyd.elec5619.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GroupPost")
public class GroupPost {

	@Id
	@GeneratedValue
	@Column(name="PostId", unique=true)
	private long id;
	
	@Column(name="GroupId")
	private long groupId;

	@Column(name="PostContent")
	private String content;
	
	@Column(name="PostCreator")
	private String creator;
	
	@Column(name="PostCreatorDisplayName")
	private String creatorDisplayName;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getGroupId() {
		return groupId;
	}
	
	public void setGroupId(long id) {
		this.groupId = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String c) {
		content = c;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String username) {
		creator = username;
	}
	
	public String getCreatorDisplayName() {
		return creatorDisplayName;
	}
	
	public void setCreatorDisplayName(String display) {
		creatorDisplayName = display;
	}
}
