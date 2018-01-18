package au.usyd.elec5619.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.UserService;

@Controller
@SessionAttributes("username")
public class LoginController {

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("userForm", new User());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors() || (!user.getPassword().equals(user.getConfirmPassword()))) {
			user.setPassword(null);
			user.setConfirmPassword(null);
			bindingResult.rejectValue("confirmPassword", "error.user", "Your password does not match");
			return "register";
		} else {
			if (this.userService.registerUser(user)) {
				return "redirect:/login";
			} else {
				return "redirect:/register?error";
			}
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "redirect:/home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		SecurityContextHolder.clearContext();
		return "redirect:/login?logout";
	}
}
