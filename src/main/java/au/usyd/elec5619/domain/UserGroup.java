package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserGroup")
public class UserGroup implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="UserGroupId", unique=true)
	private long id;

	@Column(name="UserGroupName")
	private String groupName;
	
	@Column(name="UserGroupDescription")
	private String description;
	
	@Column(name="UserGroupOwner")
	private String groupOwner;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String name) {
		groupName = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String desc) {
		description = desc;
	}
	
	public String getGroupOwner() {
		return groupOwner;
	}
	
	public void setGroupOwner(String owner) {
		groupOwner = owner;
	}
	
	/*public String toJson() {
		String id = "id : "+id;
		String groupName = "groupName : "+groupName;
		String description = "description: "+description;
		String groupOwner = "groupOwner : "+groupOwner;
		String json = "{"+id+","+groupName+","+description+","+groupOwner+"}";
		return json;
	}*/
}
