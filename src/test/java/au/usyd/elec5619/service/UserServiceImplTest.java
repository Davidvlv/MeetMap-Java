package au.usyd.elec5619.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserProfile;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	private User user;
	
	@Before
	public void setup(){
		user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setEmailAddress("mail@mail");
	}
	
	@Test
	public void registerUser(){
		userService.registerUser(user);
		assertEquals(userService.getUserByUsername("username").getUsername(), user.getUsername());
	}
	
	@Test
	public void saveUserProfileChanges(){		
		userService.registerUser(user);
		
		UserProfile userProfile = new UserProfile(user, "Description", 23);
		user.setUserProfile(userProfile);
		
		userService.saveUserProfileChanges(userProfile);
		assertEquals(userService.getUserProfileOfUser("username").getDescription(), userProfile.getDescription());
	}

}
