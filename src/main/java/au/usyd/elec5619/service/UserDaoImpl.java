package au.usyd.elec5619.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import au.usyd.elec5619.domain.ProfileImage;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserProfile;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public User getUserByUsername(String username) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from User u where u.username=:username");
		query.setString("username", username);
		User fetchedUser = (User) query.uniqueResult();
		return fetchedUser;
	}

	public UserProfile getUserProfileOfUser(String username) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		UserProfile fetchedUserProfile = (UserProfile) currentSession.get(UserProfile.class, username);
		return fetchedUserProfile;
	}

	public void saveUserProfile(UserProfile userProfile) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(userProfile);
	}

	public void save(ProfileImage profileImage) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(profileImage);
	}

	@Override
	public boolean save(User user) {
		if (getUserByUsername(user.getUsername()) == null) {
			String password = user.getPassword();
			passwordEncoder.encode(password);
			user.setPassword(passwordEncoder.encode(password));
			UserProfile userProfile = new UserProfile(user, null, null);
			this.sessionFactory.getCurrentSession().save(user);
			this.sessionFactory.getCurrentSession().save(userProfile);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ProfileImage getProfileImage(int imageId) {
		return (ProfileImage) this.sessionFactory.getCurrentSession().get(ProfileImage.class, imageId);
	}
}
