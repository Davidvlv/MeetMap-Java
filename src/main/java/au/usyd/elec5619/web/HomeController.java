package au.usyd.elec5619.web;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import au.usyd.elec5619.service.EventService;
import au.usyd.elec5619.domain.CreateEventForm;
import au.usyd.elec5619.domain.Event;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("username")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource(name = "eventService")
	EventService eventService;

	/**
	 * Returns the home view
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Principal principal) {
		model.addAttribute("createEventForm", new CreateEventForm());
		return "home";
	}

	/**
	 * Accepts a completed create group form to save the event to the database
	 */
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String createEvent(@Valid @ModelAttribute("form") CreateEventForm createEventForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		String eventName = createEventForm.getEventName();
		String description = createEventForm.getDescription();
		String startTime = createEventForm.getStartTime();
		String endTime = createEventForm.getEndTime();
		boolean isPrivate = createEventForm.getIsPrivate();
		String latitude = createEventForm.getLatitude();
		String longitude = createEventForm.getLongitude();

		String format = "yyyy-MM-dd'T'HH:mm";

		Event newEvent = new Event();

		newEvent.setStartTime(startTime, format);
		newEvent.setEndTime(endTime, format);
		newEvent.setEventName(eventName);
		newEvent.setDescription(description);
		newEvent.setHost(username);
		newEvent.setIsPrivate(isPrivate);
		newEvent.setLongitude(Double.parseDouble(longitude));
		newEvent.setLatitude(Double.parseDouble(latitude));

		try {
			eventService.createEvent(newEvent);
			return "redirect:/events";
		} catch (HibernateException e) {
			redirectAttrs.addAttribute("error", "An error occured while creating the event.");
		}

		return "createevent";
	}


	/**
	 * REST : Get all events
	 */
	@RequestMapping(value = "/rest/events/", method = RequestMethod.GET)
	@ResponseBody 
	public String getAllEvents() throws JsonProcessingException {
		
		List<Event> events = this.eventService.getAllEvents();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);
		
		return jsonInString;
	}
}
