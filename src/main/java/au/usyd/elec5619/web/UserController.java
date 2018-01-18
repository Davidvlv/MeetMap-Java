package au.usyd.elec5619.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import au.usyd.elec5619.domain.Interest;
import au.usyd.elec5619.domain.ProfileImage;
import au.usyd.elec5619.domain.UploadForm;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserProfile;
import au.usyd.elec5619.service.UserService;

@Controller
public class UserController {

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "rest/userprofile/{username}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserProfile(@PathVariable("username") String username, Model model, HttpServletResponse response,
			HttpServletRequest request, Principal principal) throws JsonProcessingException {
		UserProfile userProfile = userService.getUserProfileOfUser(username);
		ObjectMapper mapper = new ObjectMapper();
				
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userProfile);
		
		return jsonInString;
	}

	@RequestMapping(value = "/userprofile/{username}", method = RequestMethod.GET)
	public String userProfile(@PathVariable("username") String username, Model model, HttpServletResponse response,
			HttpServletRequest request, Principal principal) {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			return "redirect:/userprofile/";
		}
		model.addAttribute("username", username);
		ProfileImage profileImage = user.getUserProfile().getProfileImage();
		if (profileImage != null) {
			byte[] encodeBase64 = Base64.encode(profileImage.getProfileImage());
			String base64Encoded;
			try {
				base64Encoded = new String(encodeBase64, "UTF-8");
				model.addAttribute("userImage", base64Encoded);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("isOwnerOfProfile", username.equals(principal.getName()));
		return "userprofile";
	}

	@RequestMapping(value = "/userprofile/{username}/edit", method = RequestMethod.GET)
	public String editUserProfile(@PathVariable("username") String username, Model model, Principal principal) {
		if (!principal.getName().equals(username)) {
			return "redirect:/userprofile/" + username;
		} else {
			User user = userService.getUserByUsername(username);
			ProfileImage profileImage = user.getUserProfile().getProfileImage();
			if (profileImage != null) {
				byte[] encodeBase64 = Base64.encode(profileImage.getProfileImage());
				String base64Encoded;
				try {
					base64Encoded = new String(encodeBase64, "UTF-8");
					model.addAttribute("userImage", base64Encoded);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			model.addAttribute("user", user);
			model.addAttribute("userForm", user.getUserProfile());
			model.addAttribute("uploadForm", new UploadForm());
			return "edituserprofile";
		}
	}

	@RequestMapping(value = "/userprofile/{username}/edit", method = RequestMethod.POST)
	public String editUserProfile(@PathVariable("username") String username,
			@ModelAttribute("userForm") UserProfile userProfile, @RequestParam("file") MultipartFile file,
			BindingResult bindingResult, Model model, Principal principal) {
		userProfile.setUsername(username);
		User user = this.userService.getUserByUsername(username);
		if (!file.isEmpty()) {
			try {
				ProfileImage profileImage = userProfile.getProfileImage();
				if (profileImage != null) {
					profileImage.setProfileImage(file.getBytes());
				} else {
					profileImage = new ProfileImage(file.getBytes());
				}
				userProfile.setProfileImage(profileImage);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			userProfile.setProfileImage(user.getUserProfile().getProfileImage());
		}

		if (userProfile.getInterests() != null) {
			Iterator<Interest> iterator = userProfile.getInterests().iterator();
			while (iterator.hasNext()) {
				Interest interest = iterator.next();
				if (interest.getInterest() == null) {
					iterator.remove();
				}
			}
		}

		user.setUserProfile(userProfile);

		this.userService.saveUserProfileChanges(user.getUserProfile());
		return "redirect:/userprofile";
	}

	@RequestMapping(value = "/userprofile/{username}/profileImage", method = RequestMethod.POST)
	public String uploadFile(@PathVariable("username") String username,
			@ModelAttribute("uploadForm") UploadForm uploadForm, BindingResult result) {
		return "redirect:/userprofile/" + username + "/edit";
	}

	@RequestMapping(value = "/userprofile", method = RequestMethod.GET)
	public String userProfile(Model model, Principal principal) {
		String username = principal.getName();
		return "redirect:/userprofile/" + username;
	}

	@RequestMapping(value = "/userprofile/edit", method = RequestMethod.GET)
	public String edit(Model model, Principal principal) {
		String username = principal.getName();
		return "redirect:/userprofile/" + username + "/edit";
	}
}
