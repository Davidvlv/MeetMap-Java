package au.usyd.elec5619.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.usyd.elec5619.domain.GroupMember;
import au.usyd.elec5619.domain.UserGroup;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GroupServiceTest {

	@Autowired
	private GroupService groupService;
	
	private UserGroup savedGroup;
	
	@Before
	public void Setup() {
		UserGroup newGroup = new UserGroup();
		newGroup.setGroupName("test");
		newGroup.setDescription("test");
		newGroup.setGroupOwner("service-test-user");
		groupService.createGroup(newGroup);
		savedGroup = groupService.getOwnedGroups("service-test-user").get(0);
	}
	
	@Test
	public void addGroupMemberTest() {
		// Create a new group member to be saved
		GroupMember member = new GroupMember();
		member.setGroupId(savedGroup.getId());
		member.setMemberId("member-test");
		
		// Save the group member
		groupService.addGroupMember(member);
		
		// Assert that the member was saved
		List<String> members = groupService.getMembersOfGroup(savedGroup.getId());
		assertNotNull(members);
		assertEquals(members.get(0),"member-test");
	}
	
	@Test
	public void removeGroupMember() {
		// Create a new group member to be saved
		GroupMember member = new GroupMember();
		member.setGroupId(savedGroup.getId());
		member.setMemberId("member-test");
		groupService.addGroupMember(member);		
		
		// Remove the member from the group
		groupService.removeGroupMember(savedGroup.getId(), "member-test");
		
		// Assert that the group has no members
		List<String> members = groupService.getMembersOfGroup(savedGroup.getId());
		assertEquals(members.size(),0);
	}
}
