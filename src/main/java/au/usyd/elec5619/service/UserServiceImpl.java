package au.usyd.elec5619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.ProfileImage;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserProfile;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public boolean registerUser(User user) {
		return this.userDao.save(user);
	}

	public User getUserByUsername(String username) {
		User fetchedUser = this.userDao.getUserByUsername(username);
		return fetchedUser;
	}

	public UserProfile getUserProfileOfUser(String username) {
		UserProfile fetchedUserProfile = this.userDao.getUserProfileOfUser(username);
		return fetchedUserProfile;
	}

	@Override
	public void saveUserProfileChanges(UserProfile userProfile) {
		this.userDao.saveUserProfile(userProfile);
	}

	@Override
	public void uploadProfileImage(ProfileImage profileImage) {
		this.userDao.save(profileImage);
	}

	@Override
	public ProfileImage getProfileImage(int imageId) {
		return this.userDao.getProfileImage(imageId);
	}

}
