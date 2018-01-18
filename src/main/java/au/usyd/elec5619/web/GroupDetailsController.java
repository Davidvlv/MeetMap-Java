package au.usyd.elec5619.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.usyd.elec5619.domain.UserGroup;
import au.usyd.elec5619.domain.ViewGroupForm;
import au.usyd.elec5619.service.GroupService;
import au.usyd.elec5619.service.UserService;
import au.usyd.elec5619.domain.AddGroupPostForm;
import au.usyd.elec5619.domain.CreateGroupForm;
import au.usyd.elec5619.domain.GroupPost;
import au.usyd.elec5619.domain.User;

@Controller
public class GroupDetailsController {

	@Resource(name = "groupService")
	private GroupService groupService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	private UserGroup group;
	private User admin;
	private boolean isAdmin;
	private List<User> memberUsers;
	private List<GroupPost> posts;
	private User currentUser;

	/**
	 * Renders the details about the selected group
	 */
	@RequestMapping(value = "/groupdetails", method = RequestMethod.GET)
	public String groupDetails(Locale locale, Model model, Principal principal, 
			@ModelAttribute("groupToView") long thisGroup,
			@ModelAttribute("error") String error,
			@ModelAttribute("message") String message) {
		// Get the group
		group = groupService.getGroupById(thisGroup);
		model.addAttribute("group", group);
		
		// Get the admin of the group
		admin = userService.getUserByUsername(group.getGroupOwner());
		model.addAttribute("admin", admin);
		
		// Is the current user the admin ?
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		isAdmin = false;
		if(username.equals(group.getGroupOwner())) {
			isAdmin = true;
		}
		model.addAttribute("isAdmin", isAdmin);
		
		// Get current user
		currentUser = userService.getUserByUsername(username);
		
		// Get all members of the group
		List<String> members = groupService.getMembersOfGroup(thisGroup);
		memberUsers = new ArrayList<User>();
		for(String member : members) {
			User user = userService.getUserByUsername(member);
			memberUsers.add(user);
		}
		model.addAttribute("members", memberUsers);
		
		// Get all posts of the group
		posts = groupService.getPosts(group.getId());
		model.addAttribute("posts", posts);
		
		// Add the post form to the model
		model.addAttribute("newPostForm",new AddGroupPostForm());
		
		// Return view
		model.addAttribute("error",error);
		model.addAttribute("message",message);
		return "groupdetails";
	}
	
	/**
	 * To edit the group details
	 */
	@RequestMapping(value = "/groupdetails/edit", method = RequestMethod.POST)
	public String editGroup(RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("group", group.getId());
		return "redirect:/editgroup";
	}
	
	/**
	 * To delete the group
	 */
	@RequestMapping(value = "/groupdetails/delete", method = RequestMethod.POST)
	public String deleteGroup(RedirectAttributes redirectAttrs) {
		try {
			this.groupService.deleteGroup(group.getId());
			return "redirect:/groups";
		} catch(HibernateException exception) {
			redirectAttrs.addAttribute("error", "There was an error deleting the group");
			redirectAttrs.addAttribute("groupToView", group.getId());
			return "redirect:/groupdetails";
		}	
	}
	
	/**
	 * To add a member to the group
	 */
	@RequestMapping(value = "/groupdetails/member/add", method = RequestMethod.POST)
	public String addMember(RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("groupToView", group.getId());
		return "redirect:/addgroupmember";
	}
	
	/**
	 * To remove a member from the group
	 */
	@RequestMapping(value = "/groupdetails/member/{username}/remove", method = RequestMethod.GET)
	public String removeMember(@PathVariable("username") String username, RedirectAttributes redirectAttrs) {
		try {
			this.groupService.removeGroupMember(group.getId(), username);
			redirectAttrs.addAttribute("groupToView", group.getId());
			return "redirect:/groupdetails";
		} catch (HibernateException ex) {
			redirectAttrs.addAttribute("error", "Failed to remove the member '"+username+"' from the group");
			redirectAttrs.addAttribute("groupToView", group.getId());
			return "redirect:/groupdetails";
		}
	}
	
	/**
	 * To leave the group
	 */
	@RequestMapping(value = "/groupdetails/leave", method = RequestMethod.POST)
	public String leaveGroup(RedirectAttributes redirectAttrs) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		long groupId = group.getId();
		try {
			groupService.removeGroupMember(groupId, username);
			return "redirect:/groups";
		} catch(HibernateException ex) {
			redirectAttrs.addAttribute("error", "There was an error leaving the group");
			redirectAttrs.addAttribute("groupToView", group.getId());
			return "redirect:/groupdetails";
		}
	}
	
	/**
	 * To add a post to the group
	 */
	@RequestMapping(value = "/groupdetails/post", method = RequestMethod.POST)
	public String addPost(@Valid @ModelAttribute("newPostForm") AddGroupPostForm addGroupPostForm, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("groupToView", group.getId());
		
		if (bindingResult.hasErrors()) {
			redirectAttrs.addAttribute("error", "You must enter content to post");
			return "redirect:/groupdetails";
		} else {
			try {
				long groupId = group.getId();
				
				GroupPost post = new GroupPost();
				post.setContent(addGroupPostForm.getContent());
				post.setCreator(currentUser.getUsername());
				post.setCreatorDisplayName(currentUser.getUserProfile().getFirstName()+
										  currentUser.getUserProfile().getLastName());
				post.setGroupId(groupId);
				groupService.addPostToGroup(post);
				
				return "redirect:/groupdetails";
			} catch (HibernateException e) {
				redirectAttrs.addAttribute("error", "An error occured while creating groups");
				return "redirect:/groupdetails";
			}
		}
	}
}
