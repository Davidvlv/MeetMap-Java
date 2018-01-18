package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.GroupMember;
import au.usyd.elec5619.domain.GroupPost;
import au.usyd.elec5619.domain.UserGroup;

@Service
@Transactional
public class GroupDaoImpl implements GroupDao {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void createGroup(UserGroup newGroup) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(newGroup);
	}
	
	@Override
	public void updateGroup(UserGroup updatedGroup) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(updatedGroup);
	}
	
	@Override
	public void deleteGroup(long groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		
		String deleteAllGroupMembers = "DELETE FROM GroupMember G WHERE G.groupId = '"+groupId+"'";
		Query deleteAllGroupMembersQuery = session.createQuery(deleteAllGroupMembers);
		deleteAllGroupMembersQuery.executeUpdate();
		
		String deleteGroup = "DELETE FROM UserGroup G WHERE G.id = '"+groupId+"'";
		Query deleteGroupQuery = session.createQuery(deleteGroup);
		deleteGroupQuery.executeUpdate();
	}

	@Override
	public List<UserGroup> getOwnedGroups(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM UserGroup G WHERE G.groupOwner = '"+username+"'";
		Query query = session.createQuery(hql);
		List results = query.list();
		return results;
	}

	@Override
	public List<UserGroup> getGroupsBelongedTo(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM GroupMember G WHERE G.member = '"+username+"'";
		Query query = session.createQuery(hql);
		List<GroupMember> groupIds = query.list();
		List<UserGroup> groups = new ArrayList<UserGroup>();
		for(GroupMember g : groupIds) {
			UserGroup group = this.getGroupById(g.getGroupId());
			groups.add(group);
		}
		return groups;
	}

	@Override
	public UserGroup getGroupById(long groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM UserGroup G WHERE G.id = '"+groupId+"'";
		Query query = session.createQuery(hql);
		UserGroup group = (UserGroup) query.uniqueResult();
		return group;
	}

	@Override
	public List<String> getMembersOfGroup(long groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT G.member FROM GroupMember G WHERE G.groupId = '"+groupId+"'";
		Query query = session.createQuery(hql);
		List members = query.list();
		return members;
	}

	@Override
	public void addGroupMember(GroupMember groupMember) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(groupMember);
	}

	@Override
	public void removeGroupMember(long groupId, String memberId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "DELETE FROM GroupMember G WHERE G.groupId = '"+groupId+"' AND G.member = '"+memberId+"'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void addPostToGroup(GroupPost post) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(post);
	}

	@Override
	public void removePostFromGroup(long groupId, long postId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "DELETE FROM GroupPost P WHERE P.groupId = '"+groupId+"' AND P.id = '"+postId+"'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List<GroupPost> getPosts(long groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM GroupPost P WHERE P.groupId = '"+groupId+"'";
		Query query = session.createQuery(hql);
		List posts = query.list();
		return posts;
	}

	@Override
	public void deleteAllGroups(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<UserGroup> groups = getOwnedGroups(username);
		for(UserGroup g : groups) {
			long id = g.getId();
			
			String deleteAllGroupMembers = "DELETE FROM GroupMember G WHERE G.groupId = '"+id+"'";
			Query deleteAllGroupMembersQuery = session.createQuery(deleteAllGroupMembers);
			deleteAllGroupMembersQuery.executeUpdate();
		}
		
		String deleteAllGroups = "DELETE FROM UserGroup G WHERE G.groupOwner = '"+username+"'";
		Query deleteAllGroupsQuery = session.createQuery(deleteAllGroups);
		deleteAllGroupsQuery.executeUpdate();
	}
}
