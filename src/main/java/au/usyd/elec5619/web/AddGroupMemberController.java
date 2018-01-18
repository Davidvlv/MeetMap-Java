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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.usyd.elec5619.domain.AddGroupMemberForm;
import au.usyd.elec5619.domain.CreateGroupForm;
import au.usyd.elec5619.domain.GroupMember;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserGroup;
import au.usyd.elec5619.service.GroupService;
import au.usyd.elec5619.service.UserService;

@Controller
public class AddGroupMemberController {
	@Resource(name = "groupService")
	private GroupService groupService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	private UserGroup group;
	
	/**
	 * Renders the page to add a new group member
	 */
	@RequestMapping(value = "/addgroupmember", method = RequestMethod.GET)
	public String addGroupMember(Locale locale, Model model, Principal principal, 
			@ModelAttribute("groupToView") long thisGroup,
			@ModelAttribute("error") String error) {
		// Get the group
		group = groupService.getGroupById(thisGroup);
		model.addAttribute("group", group);
		
		// Create the form
		AddGroupMemberForm form = new AddGroupMemberForm();
		model.addAttribute("form",form);
		
		// Return view
		model.addAttribute("error",error);
		return "addgroupmember";
	}
	
	/**
	 * To add the new group member
	 */
	@RequestMapping(value = "/addgroupmember", method = RequestMethod.POST)
	public String addGroupMember(@Valid @ModelAttribute("form") AddGroupMemberForm addGroupMemberForm, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("groupToView", group.getId());
		
		// Get the user to add to the group
		String userToAdd = addGroupMemberForm.getMember();
		
		// Add the user to the group
		try {
			// Is the user already a part of the group ?
			List<String> members = groupService.getMembersOfGroup(group.getId());
			for(String member : members) {
				if(member.equals(userToAdd)) {
					redirectAttrs.addAttribute("error", "That user is already a part of the group");
					return "redirect:/addgroupmember";
				}
			}
			String admin = group.getGroupOwner();
			if(admin.equals(userToAdd)) {
				redirectAttrs.addAttribute("error", "That user is the admin of the group");
				return "redirect:/addgroupmember";
			}
			
			// Does the user exist ?
			User user = userService.getUserByUsername(userToAdd);
			if(user==null) {
				redirectAttrs.addAttribute("error", "That user does not exist");
				return "redirect:/addgroupmember";
			}
			
			GroupMember groupMember = new GroupMember();
			groupMember.setGroupId(group.getId());
			groupMember.setMemberId(userToAdd);
			groupService.addGroupMember(groupMember);
			
			String userDisplayName = user.getUserProfile().getFirstName()+" "+user.getUserProfile().getLastName();
			redirectAttrs.addAttribute("message", "The user '"+userDisplayName+"' has been added to the group !");
			return "redirect:/groupdetails";
		} catch (HibernateException exception) {
			redirectAttrs.addAttribute("error", "Error adding user to the group");
			return "redirect:/addgroupmember";
		}
	}
}
