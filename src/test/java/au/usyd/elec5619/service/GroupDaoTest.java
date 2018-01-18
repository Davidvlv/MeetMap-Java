package au.usyd.elec5619.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserGroup;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class GroupDaoTest {
	
	@Autowired
	private GroupDao groupDao;
	
	@Test
	public void testCreateGroup() {
		// Create the group to be saved
		UserGroup newGroup = new UserGroup();
		newGroup.setGroupName("test");
		newGroup.setDescription("test");
		newGroup.setGroupOwner("create-test-user");
		
		// Save the group
		groupDao.createGroup(newGroup);
		
		// Assert that the user "test-user" has groups saved
		List<UserGroup> savedGroups = groupDao.getOwnedGroups("create-test-user");
		assertNotNull(savedGroups);
		assertEquals(savedGroups.get(0).getGroupName(),newGroup.getGroupName());
	}
	
	@Test
	public void testEditGroup() {
		// Save a group
		UserGroup newGroup = new UserGroup();
		newGroup.setGroupName("test");
		newGroup.setDescription("test");
		newGroup.setGroupOwner("edit-test-user");				
		groupDao.createGroup(newGroup);
		
		// Get the group to edit it
		UserGroup savedGroup = groupDao.getOwnedGroups("edit-test-user").get(0);
		
		// Update the group
		savedGroup.setGroupName("UPDATE");
		groupDao.updateGroup(savedGroup);
		
		// Assert that the group has been updated
		long id = savedGroup.getId();
		UserGroup updatedGroup = groupDao.getGroupById(id);
		assertNotNull(updatedGroup);
		assertEquals(updatedGroup.getGroupName(),"UPDATE");
	}
	
	@Test
	public void testDeleteGroup() {
		// Save a group
		UserGroup newGroup = new UserGroup();
		newGroup.setGroupName("test");
		newGroup.setDescription("test");
		newGroup.setGroupOwner("delete-test-user");				
		groupDao.createGroup(newGroup);

		// Assert that the group has been saved
		UserGroup savedGroup = groupDao.getOwnedGroups("delete-test-user").get(0);
		assertNotNull(savedGroup);
		assertEquals(savedGroup.getGroupName(),newGroup.getGroupName());
		
		// Delete the group
		groupDao.deleteGroup(savedGroup.getId());
		
		// Assert that the group no longer exists
		List<UserGroup> savedGroups = groupDao.getOwnedGroups("delete-test-user");
		assertEquals(savedGroups.size(),0);
	}

}
