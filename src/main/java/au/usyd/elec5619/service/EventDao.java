package au.usyd.elec5619.service;

import au.usyd.elec5619.domain.Event;
//import au.usyd.elec5619.domain.Interest;

import java.util.List;


public interface EventDao {
	
	//Create-Update-Delete Methods for event
	public void createEvent(Event event);
	public void updateEvent(Event event);
	public void deleteEvent(long eventId);
	public void deleteAllEvents();
	
	
	public List<Event> getAllEvents();
	public List<Event> getAllPublicEvents();
	
	public Event getEvent(long eventId);
	
	
	//Get events for a given user
	public List<Event> getPastEvents();
	public List<Event> getUpcomingEvents();
	public List<Event> getHostedEvents(String username);	
	
	
	//public List<Event> getInvitedEvents(String username);
	//public List<String> getEventAttendees(Event event);
	//public List<Event> getEventsWithInterest(Interest interest);
	//public boolean inviteUser(String username);
	//public boolean uninviteUser(String username);
	//public List<Interest> getEventsWithInterst(Interest interest)
	
	
}