package au.usyd.elec5619.web;

import java.security.Principal;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import au.usyd.elec5619.service.EventService;
import au.usyd.elec5619.domain.CreateEventForm;
import au.usyd.elec5619.domain.Event;


@Controller
public class EventController {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	
	@Resource(name="eventService")
	EventService eventService;
	
	@RequestMapping(value="/events", method=RequestMethod.GET)
	public String viewPublicEvents(Locale locale, Model model, Principal principal){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		model.addAttribute("username", username);
		
		List<Event> events = eventService.getAllPublicEvents();
		
		String result = "";
		for(Event e : events){
			String eventName = e.getEventName();
			result += (eventName + ", ");
		}
		
		logger.info(result);
		model.addAttribute("events", events);
		
		return "viewevents";
	}
	
	@RequestMapping(value="/events/hosting", method=RequestMethod.GET)
	public String viewHostingEvents(Locale locale, Model model, Principal principal){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		List<Event> events = eventService.getHostedEvents(username);
		
		model.addAttribute("events", events);
		return "viewevents";
	}
	
	
	
	@RequestMapping(value="/events/{id}", method=RequestMethod.GET)
	public String viewEvent(@PathVariable("id") long id, Locale locale, Model model, Principal principal, RedirectAttributes redirectAttrs){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Event event = eventService.getEventById(id);
		if (event.getHost().equals(username)){
			model.addAttribute("canEdit", true);
		}
		model.addAttribute("event",event);
		return "eventdetails";
	}
	
	@RequestMapping(value="/events/{id}/edit", method=RequestMethod.GET)
	public String editEventForm(@PathVariable("id") long id, Locale locale, Model model, Principal principle, RedirectAttributes redirectAttrs){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Event event = eventService.getEventById(id);
		model.addAttribute("event",event);
	
		if( event.getHost().equals(username)){
			model.addAttribute("createEventForm", new CreateEventForm());
			return "editevent";
		} else {
			return "redirect:/elec5619/events/";
		}
	}
	
	@RequestMapping(value="/events/{id}/edit", method=RequestMethod.POST)
	public String editEvent(@PathVariable("id") long id, Locale locale, Model model, Principal principal, RedirectAttributes redirectAttrs){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Event event = eventService.getEventById(id);
		if(!event.getHost().equals(username)){
			return "redirect:/events/"+id;
		} else {
			
		}
		
		return "redirect:/events/"+id;
	}
	
	
	@RequestMapping(value="/events/{id}/delete", method=RequestMethod.POST)
	public String deleteEvent(@PathVariable("id") long id, Locale locale, Model model, Principal principle, RedirectAttributes redirectAttrs){		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		Event event = eventService.getEventById(id);
		
		if(username.equals(event.getHost())){
			eventService.deleteEvent(id);
		}
		
		return "redirect:/events";
	}
	//@RequestMapping(value="/events/{id}/invite")
	
	
	@RequestMapping(value="/events/add", method=RequestMethod.GET)
	public String createEventPage(Locale locale, Model model, Principal principal){
		model.addAttribute("createEventForm", new CreateEventForm());
		return "createevent";
		
	}
	
	@RequestMapping(value="/events/add", method=RequestMethod.POST)
	public String createEvent(@Valid @ModelAttribute("form") CreateEventForm createEventForm, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs ) throws Exception{
		
		
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
	
}
