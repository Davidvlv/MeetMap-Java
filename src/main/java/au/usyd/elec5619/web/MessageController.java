package au.usyd.elec5619.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.Message;
import au.usyd.elec5619.service.UserService;
import au.usyd.elec5619.service.MessageService;


@Controller
@SessionAttributes("userDetails")
public class MessageController {
	
	@Resource(name = "messageService")
	private MessageService messageService;
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String register(Model model, Principal principal) {
		String username = (String) principal.getName();
		model.addAttribute("messageForm", new Message());
		model.addAttribute("username", username);
		return "message";
	}
	
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public String inbox(Model model, Principal principal) {
		//String username = (String) model.asMap().get("userDetails");
		String username = (String) principal.getName();
		System.out.println(username);
		model.addAttribute("inbox", this.messageService.getInboxByUsername(username));
		model.addAttribute("username", username);
		return "inbox";
	}
	
	@RequestMapping(value = "/outbox", method = RequestMethod.GET)
	public String outbox(Model model, Principal principal) {
		String username = principal.getName();
		//String username = (String) model.asMap().get("userDetails");
		model.addAttribute("outbox", this.messageService.getOutboxByUsername(username));
		model.addAttribute("username", username);
		return "outbox";
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("messageForm") Message messageForm, BindingResult bindingResult, Model model, Principal principal) {
		if (bindingResult.hasErrors()) {
			// If there are errors in the form, we want to remain on the
			// New Message page
			return "message";
		} else {
			// Insert the new user into the database
			this.messageService.save(messageForm);
			return "redirect:/inbox";
		}
	}
	
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public String showMessage(@PathVariable("id") long id, Model model){
		model.addAttribute("message", this.messageService.getMessageById(id) );
		return "messageDetail";
	}

}
