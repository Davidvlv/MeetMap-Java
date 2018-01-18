package au.usyd.elec5619.service;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import au.usyd.elec5619.domain.Event;


@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EventDaoTest {
	
	@Autowired
	private EventDao eventDao;
	
	private Event createTestEvent(String host, String eventname, String description,
								  String startTime, String endTime, boolean isPrivate) throws Exception
		{
		
		Event event = new Event();
		
		event.setHost(host);
		event.setEventName(eventname);
		event.setDescription(description);
		event.setStartTime(startTime, "dd-M-YYYY hh:mm");
		event.setEndTime(endTime, "dd-M-YYYY hh:mm");
		event.setIsPrivate(isPrivate);
		
		return event;
	}
	
	@Test
	public void testCreateEvent() throws Exception{
		Event event = createTestEvent("test-user", "test-event-name", "hello",
									  "28-10-2017 18:00", "29-10-2017 00:00", false);
		
		long eventId = event.getId();
		
		eventDao.createEvent(event);
		
		System.out.println("Event with eventID " + eventId + " Was created"); 
		
		
		Event recievedEvent = eventDao.getAllEvents().get(0);
		System.out.println(recievedEvent);
		System.out.println(recievedEvent.getEventName());
		System.out.println(event.getEventName());
		
		System.out.println(recievedEvent.getEventName().equals(event.getEventName()));
		
		
		assertTrue(recievedEvent.getEventName().equals(event.getEventName()));
	
		System.out.println("Events are the same");
		System.out.println(recievedEvent.getId());
		
		
		eventDao.deleteEvent(recievedEvent.getId());
		
	}
	
	@Test
	public void testUpdateEvent() throws Exception{
		assertTrue(true);
		
		Event event = createTestEvent("test-user", "test-event-name", "hello",
									"28-10-2017 18:00", "29-10-2017 00:00", false);

		eventDao.createEvent(event);
		
		event.setStartTime("30-10-2017 18:00", "dd-M-YYYY hh:mm");
		event.setEndTime("31-10-2017 00:00", "dd-M-YYYY hh:mm");

		eventDao.updateEvent(event);
		
		
		List<Event> events = eventDao.getAllPublicEvents();
		assertNotNull(events);
		Event recievedEvent = events.get(0);
		
		
		assertEquals(event.getStartTime(), recievedEvent.getStartTime());
		assertEquals(event.getEndTime(), recievedEvent.getEndTime());
		
		eventDao.deleteEvent(recievedEvent.getId());
	}
	
	@Test
	public void testGetAllPublicEvents() throws Exception{
		Event event1 = createTestEvent("test-user", "test-event-name", "hello",
				"28-10-2017 18:00", "29-10-2017 00:00", false);
		Event event2 = createTestEvent("test-user", "test-event-name", "hello",
				"28-10-2017 18:00", "29-10-2017 00:00", true);
		Event event3 = createTestEvent("test-user", "test-event-name", "hello",
				"28-10-2017 18:00", "29-10-2017 00:00", true);
		eventDao.createEvent(event1);
		eventDao.createEvent(event2);
		eventDao.createEvent(event3);
		
		List<Event> events = eventDao.getAllPublicEvents();
		
		for (Event event : events) {
			assertFalse(event.getIsPrivate());
		}
		
		eventDao.deleteAllEvents();
	}
	
	@Test
	public void testGetHostedEvents() throws Exception {
		
		System.out.println("Testing Get Hosted Events");
		Event event1 = createTestEvent("test-user", "test-event-name", "hello",
				"28-10-2017 18:00", "29-10-2017 00:00", false);
		Event event2 = createTestEvent("test-user-2", "test-event-name", "hello",
				"28-10-2017 18:00", "29-10-2017 00:00", false);
		Event event3 = createTestEvent("test-user-2", "test-event-name", "hello",
				"28-10-2017 18:00", "29-10-2017 00:00", false);
		
		
		eventDao.createEvent(event1);
		eventDao.createEvent(event2);
		eventDao.createEvent(event3);
		
		
		
		List<Event> events = eventDao.getHostedEvents("test-user-2");
	
		for (Event e : events) {
			System.out.println(e.getHost());
			assertEquals(e.getHost(), "test-user-2");
		}
		
		eventDao.deleteAllEvents();
		
	}
	
	
	
	
	@Test 
	public void testDeleteAllEvents() 
	{
		System.out.println("Deleting all events");
		eventDao.deleteAllEvents();
		
		System.out.println("All Events Deleted");
		
		List<Event> allEvents = eventDao.getAllEvents();
		
		assertTrue(allEvents.size() == 0);
	}
	
	
	
}
