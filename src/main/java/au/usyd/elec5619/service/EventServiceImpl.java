package au.usyd.elec5619.service;

import java.util.List;

import au.usyd.elec5619.domain.Event;
import au.usyd.elec5619.domain.Interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value="eventService")
@Transactional
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventDao eventDao;

	@Override
	public void createEvent(Event event) {
		eventDao.createEvent(event);
	}

	@Override
	public void updateEvent(Event event) {
		eventDao.updateEvent(event);
	}

	@Override
	public void deleteEvent(long id) {
		eventDao.deleteEvent(id);
	}

	@Override
	public List<Event> getAllPublicEvents() {
		return eventDao.getAllPublicEvents();
	}
	
	@Override
	public List<Event> getAllEvents(){
		return eventDao.getAllEvents();
	}
	
	@Override
	public List<Event> getHostedEvents(String username){
		return eventDao.getHostedEvents(username);
	}

	@Override
	public Event getEventById(long eventId) {
		return eventDao.getEvent(eventId);
	}
	
	
	
}