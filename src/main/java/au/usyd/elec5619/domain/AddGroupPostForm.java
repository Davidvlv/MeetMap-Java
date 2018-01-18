package au.usyd.elec5619.domain;

import org.hibernate.validator.constraints.NotBlank;

public class AddGroupPostForm {
	@NotBlank(message = "Please enter content for your post")
	private String content;
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String c) {
		this.content = c;
	}
}
