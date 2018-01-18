package au.usyd.elec5619.domain;

import org.hibernate.validator.constraints.NotBlank;

public class ViewGroupForm {
	@NotBlank
	private long groupToView;
	
	public long getGroupToView() {
		return this.groupToView;
	}
	
	public void setGroupToView(long g) {
		this.groupToView = g;
	}
}
