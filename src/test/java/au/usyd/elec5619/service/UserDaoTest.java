package au.usyd.elec5619.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserProfile;


@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSaveUser(){
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setEmailAddress("mail@mail");
		
		userDao.save(user);
		assertEquals(user.getUsername(), userDao.getUserByUsername("username").getUsername());
	}
	
	@Test
	public void testGetUserThatDoesNotExist(){
		String randomUsername = "random" + (Math.random() * 1000);
		assertEquals(null, userDao.getUserByUsername(randomUsername));
	}

}
