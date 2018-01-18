package au.usyd.elec5619.web;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.usyd.elec5619.domain.CreateGroupForm;
import au.usyd.elec5619.domain.UserGroup;
import au.usyd.elec5619.domain.ViewGroupForm;
import au.usyd.elec5619.service.GroupService;

@Controller
public class ViewGroupsController {
	
	@Resource(name = "groupService")
	private GroupService groupService;

	/**
	 * Renders the page to view your groups
	 */
	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String viewGroups(Locale locale, Model model, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		// Get all the owned groups
		List<UserGroup> ownedGroups = this.groupService.getOwnedGroups(username);
		model.addAttribute("ownedGroups", ownedGroups);
		// Get all the groups they belong to
		List<UserGroup> groupsBelongedTo = this.groupService.getGroupsBelongedTo(username);
		model.addAttribute("groups", groupsBelongedTo);
		// Add the form
		model.addAttribute("viewGroupForm", new ViewGroupForm());
		// Return view
		return "viewgroups";
	}
	
	/**
	 * Redirects to a particular group's detail page
	 */
	@RequestMapping(value = "/groups", method = RequestMethod.POST)
	public String viewGroups(@ModelAttribute("viewGroupForm") ViewGroupForm viewGroupForm, 
			BindingResult bindingResult, Model mode, RedirectAttributes redirectAttrs) {
		long groupId = viewGroupForm.getGroupToView();
		redirectAttrs.addAttribute("groupToView", groupId);
		return "redirect:/groupdetails";
	}
}
