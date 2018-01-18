package au.usyd.elec5619.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UserProfileTest {
	
	@Test
	public void userProfileCreation(){
		User user = new User();
		UserProfile userProfile = new UserProfile(user, null, null);
		assertEquals(userProfile.getUser(), user);
		assertEquals(userProfile.getAge(), null);
		assertEquals(userProfile.getDescription(), null);
	}
	
	@Test
	public void setValidAge(){
		UserProfile userProfile = new UserProfile();
		userProfile.setAge(23);
		assertEquals(userProfile.getAge(), (Integer) 23);
	}
	
	@Test
	public void setValidDescription(){
		UserProfile userProfile = new UserProfile();
		userProfile.setDescription("Description");
		assertEquals(userProfile.getDescription(), "Description");
	}
	
	@Test
	public void setValidInterests(){
		UserProfile userProfile = new UserProfile();
		List<Interest> interests = new ArrayList<Interest>();
		
		Interest sportInterest = new Interest();
		sportInterest.setInterest("sport");
		Interest swimmingInterest = new Interest();
		swimmingInterest.setInterest("swimming");
		
		interests.add(sportInterest);
		interests.add(swimmingInterest);
		
		userProfile.setInterests(interests);
		
		assertEquals(userProfile.getInterests(), interests);
	}

}
