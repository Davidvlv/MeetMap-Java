package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.ProfileImage;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserProfile;

public interface UserDao {
	public User getUserByUsername(String username);

	public boolean save(User user);

	public void saveUserProfile(UserProfile userProfile);

	public UserProfile getUserProfileOfUser(String username);

	public void save(ProfileImage profileImage);

	public ProfileImage getProfileImage(int imageId);
}
