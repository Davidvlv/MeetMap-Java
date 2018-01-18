package au.usyd.elec5619.web;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import au.usyd.elec5619.domain.CreateGroupForm;
import au.usyd.elec5619.domain.UserGroup;
import au.usyd.elec5619.service.GroupService;

@Controller
@SessionAttributes("username")
public class CreateGroupController {
	
	@Resource(name = "groupService")
	private GroupService groupService;
	
	private String currentUser;

	/**
	 * Renders the page to create a group
	 */
	@RequestMapping(value = "/creategroup", method = RequestMethod.GET)
	public String createGroup(Locale locale, Model model, Principal principal) {
		model.addAttribute("createGroupForm", new CreateGroupForm());
		return "creategroup";
	}
	
	/**
	 * Accepts a completed create group form to save the group to the database
	 */
	@RequestMapping(value = "/creategroup", method = RequestMethod.POST)
	public String createGroup(@Valid @ModelAttribute("createGroupForm") CreateGroupForm createGroupForm, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "creategroup";
		} else {
			// Get the group object
			UserGroup newGroup = createGroupForm.toGroup();
			// Get the current user's username
			if(getCurrentUser() == null) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				setCurrentUser(authentication.getName());
			}
			// Add the current user as the owner of the group
			newGroup.setGroupOwner(currentUser);
			// Save it using the group service
			try {
				this.groupService.createGroup(newGroup);
				return "redirect:/groups";
			} catch(HibernateException exception) {
				return "redirect:/creategroup?error";
			}	
		}
	}
	
	public String getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(String username) {
		currentUser = username;
	}
}
