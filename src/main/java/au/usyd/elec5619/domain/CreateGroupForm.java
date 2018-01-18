package au.usyd.elec5619.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CreateGroupForm {
	@NotBlank(message = "Please enter a name for the group.")
	@Size(min = 1, message = "Your must enter a group name")
	private String groupName;

	@NotBlank(message = "Please enter a password.")
	@Size(min = 1, message = "You must enter a description")
	private String description;
	
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
	
	public UserGroup toGroup() {
		UserGroup newGroup = new UserGroup();
		newGroup.setGroupName(groupName);
		newGroup.setDescription(description);
		return newGroup;
	}
}
