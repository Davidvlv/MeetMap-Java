package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.ProfileImage;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserProfile;

public interface UserService {

	public boolean registerUser(User user);

	public User getUserByUsername(String username);

	public UserProfile getUserProfileOfUser(String username);

	public void saveUserProfileChanges(UserProfile userProfile);

	public void uploadProfileImage(ProfileImage profileImage);

	public ProfileImage getProfileImage(int imageId);

}
