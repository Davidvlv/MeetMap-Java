package au.usyd.elec5619.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {

	@Test
	public void setValidUsername() {
		User user = new User();
		user.setUsername("username");
		assertEquals("username", user.getUsername());
	}

	@Test
	public void setValidPassword() {
		User user = new User();
		user.setPassword("password");
		assertEquals("password", user.getPassword());
	}

	@Test
	public void setValidEmail() {
		User user = new User();
		user.setEmailAddress("mail@mail");
		assertEquals("mail@mail", user.getEmailAddress());
	}

}