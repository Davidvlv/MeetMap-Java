package au.usyd.elec5619.service;

import java.util.List;

import au.usyd.elec5619.domain.GroupMember;
import au.usyd.elec5619.domain.GroupPost;
import au.usyd.elec5619.domain.UserGroup;

public interface GroupDao {
	public void createGroup(UserGroup newGroup);
	public void updateGroup(UserGroup updatedGroup);
	public void deleteGroup(long groupId);
	public List<UserGroup> getOwnedGroups(String username);
	public List<UserGroup> getGroupsBelongedTo(String username);
	public UserGroup getGroupById(long groupId);
	public List<String> getMembersOfGroup(long groupId);
	public void addGroupMember(GroupMember groupMember);
	public void removeGroupMember(long groupId, String memberId);
	public List<GroupPost> getPosts(long groupId);
	public void addPostToGroup(GroupPost post);
	public void removePostFromGroup(long groupId, long postId);
	public void deleteAllGroups(String username);
}
