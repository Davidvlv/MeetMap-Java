package au.usyd.elec5619.domain;

import org.hibernate.validator.constraints.NotBlank;

public class AddGroupMemberForm {
	@NotBlank(message = "Please enter a user you want to add to the group.")
	private String member;
	
	public String getMember() {
		return this.member;
	}
	
	public void setMember(String username) {
		this.member = username;
	}
}
