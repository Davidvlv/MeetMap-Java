package au.usyd.elec5619.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserGroupTest {
	
	@Test
	public void setGroupNameTest() {
		UserGroup group = new UserGroup();
		group.setGroupName("group-name-test");
		
		assertEquals(group.getGroupName(),"group-name-test");
	}
	
	@Test
	public void setGroupDescriptionTest() {
		UserGroup group = new UserGroup();
		group.setDescription("group-description-test");
		
		assertEquals(group.getDescription(),"group-description-test");
	}
	
	@Test
	public void setGroupOwnerTest() {
		UserGroup group = new UserGroup();
		group.setGroupOwner("group-owner-test");
		
		assertEquals(group.getGroupOwner(),"group-owner-test");
	}
}
