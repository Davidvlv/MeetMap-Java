package au.usyd.elec5619.web;

import java.security.Principal;
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

import au.usyd.elec5619.domain.CreateGroupForm;
import au.usyd.elec5619.domain.UserGroup;
import au.usyd.elec5619.service.GroupService;

@Controller
public class EditGroupController {
	
	@Resource(name = "groupService")
	private GroupService groupService;
	
	private UserGroup group;
	
	/**
	 * Renders the page to create a group
	 */
	@RequestMapping(value = "/editgroup", method = RequestMethod.GET)
	public String editGroup(Locale locale, Model model, Principal principal,
			@ModelAttribute("group") long groupId) {
		// Get the group
		group = groupService.getGroupById(groupId);
		
		// Create the form
		CreateGroupForm form = new CreateGroupForm();
		form.setGroupName(group.getGroupName());
		form.setDescription(group.getDescription());
		model.addAttribute("editGroupForm", form);
		
		// Return the view
		return "editgroup";
	}
	
	/**
	 * Accepts a completed create group form to save the group to the database
	 */
	@RequestMapping(value = "/editgroup", method = RequestMethod.POST)
	public String editGroup(@Valid @ModelAttribute("editGroupForm") CreateGroupForm createGroupForm, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		if (bindingResult.hasErrors()) {
			return "editgroup";
		} else {
			// Update the group with the new data
			group.setGroupName(createGroupForm.getGroupName());
			group.setDescription(createGroupForm.getDescription());
			
			// Update the group using the group service
			try {
				this.groupService.updateGroup(group);
				redirectAttrs.addAttribute("groupToView", group.getId());
				redirectAttrs.addAttribute("message", "The group information has been edited !");
				return "redirect:/groupdetails";
			} catch(HibernateException exception) {
				return "redirect:/editgroup?error";
			}	
		}
	}
}
