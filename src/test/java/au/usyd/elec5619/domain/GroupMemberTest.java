package au.usyd.elec5619.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupMemberTest {
	
	@Test
	public void setGroupTest() {
		GroupMember m = new GroupMember();
		m.setGroupId(9999);
		
		assertEquals(m.getGroupId(),9999);
	}
	
	@Test
	public void setGroupMemberTest() {
		GroupMember m = new GroupMember();
		m.setMemberId("group-member-test");
		
		assertEquals(m.getMemberId(),"group-member-test");
	}
}
