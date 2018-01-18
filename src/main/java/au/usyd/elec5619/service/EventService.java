package au.usyd.elec5619.service;

import java.util.List;

import au.usyd.elec5619.domain.Event;

public interface EventService {

	public void createEvent(Event event);
	
	public void updateEvent(Event event);
	
	public void deleteEvent(long id);
	
	public List<Event> getAllPublicEvents();
	
	public List<Event> getAllEvents();

	public List<Event> getHostedEvents(String username);
	
	public Event getEventById(long eventId);
}
