package au.usyd.elec5619.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.GroupMember;
import au.usyd.elec5619.domain.GroupPost;
import au.usyd.elec5619.domain.UserGroup;

@Service(value = "groupService")
@Transactional
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDao groupDao;

	@Override
	public void createGroup(UserGroup newGroup) {
		this.groupDao.createGroup(newGroup);
	}
	
	@Override
	public void updateGroup(UserGroup updatedGroup) {
		this.groupDao.updateGroup(updatedGroup);
	}
	
	@Override
	public void deleteGroup(long groupId) {
		this.groupDao.deleteGroup(groupId);
	}

	@Override
	public List<UserGroup> getOwnedGroups(String username) {
		return this.groupDao.getOwnedGroups(username);
	}

	@Override
	public List<UserGroup> getGroupsBelongedTo(String username) {
		return this.groupDao.getGroupsBelongedTo(username);
	}

	@Override
	public UserGroup getGroupById(long id) {
		return this.groupDao.getGroupById(id);
	}

	@Override
	public List<String> getMembersOfGroup(long id) {
		return this.groupDao.getMembersOfGroup(id);
	}

	@Override
	public void addGroupMember(GroupMember groupMember) {
		this.groupDao.addGroupMember(groupMember);
	}

	@Override
	public void removeGroupMember(long groupId, String memberId) {
		this.groupDao.removeGroupMember(groupId, memberId);
	}

	@Override
	public void addPostToGroup(GroupPost post) {
		this.groupDao.addPostToGroup(post);
	}

	@Override
	public void removePostFromGroup(long groupId, long postId) {
		this.groupDao.removePostFromGroup(groupId, postId);
	}

	@Override
	public List<GroupPost> getPosts(long groupId) {
		return this.groupDao.getPosts(groupId);
	}

	@Override
	public void deleteAllGroups(String username) {
		this.groupDao.deleteAllGroups(username);
	}

}
