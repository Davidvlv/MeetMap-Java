package au.usyd.elec5619.service;

import java.util.List;

import au.usyd.elec5619.domain.Event;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EventDaoImpl implements EventDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	@Override
	public void createEvent(Event newEvent) {
		Session session = sessionFactory.getCurrentSession();
		session.save(newEvent);
	}

	@Override
	public void updateEvent(Event updatedEvent) {
		Session session = sessionFactory.getCurrentSession();
		session.update(updatedEvent);
	}

	@Override
	public void deleteEvent(long eventId) {
		Session session = sessionFactory.getCurrentSession();
		String deleteEventQueryString = "DELETE FROM Event E WHERE E.id = "+eventId;
		Query deleteEventQuery = session.createQuery(deleteEventQueryString);
		deleteEventQuery.executeUpdate();
	}

	
	@Override
	public List<Event> getAllPublicEvents(){
		
		Session session = sessionFactory.getCurrentSession();
		String getAllPublicQueryString = "FROM Event E Where E.isPrivate is false";
		Query getAllPublicQuery = session.createQuery(getAllPublicQueryString);
		List results = getAllPublicQuery.list();

		return results;
	}

	@Override
	public List<Event> getPastEvents() {
		// TODO FINISH THIS FUNCTION
		Session session = sessionFactory.getCurrentSession();
		//currentTime = new Date()
		//String getPastEventsQueryString = "FROM Event E WHERE E.endTime BEFORE  "
		return null;
	}

	@Override
	public List<Event> getHostedEvents(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String getHostedEvents = "FROM Event E WHERE E.host = '"+username+"'";
		Query getHostedEventsQuery = session.createQuery(getHostedEvents);
		List results = getHostedEventsQuery.list();
		return results;
	}


//	@Override
//	public List<Event> getInvitedEvents(String username) {
//		
//		return null;
//	}


	@Override
	public void deleteAllEvents() {
		Session session = sessionFactory.getCurrentSession();
		
		String deleteAllEvents = "DELETE FROM Event";
		Query deleteAllEventsQuery = session.createQuery(deleteAllEvents);
		deleteAllEventsQuery.executeUpdate();
	}


	@Override
	public List<Event> getUpcomingEvents() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Event> getAllEvents() {
		Session session = sessionFactory.getCurrentSession();
		String getAllEvents = "FROM Event E";
		Query getAllEventsQuery = session.createQuery(getAllEvents);		
		List results =  getAllEventsQuery.list();
		return results;
	}


	@Override
	public Event getEvent(long eventId) {
		Session session = sessionFactory.getCurrentSession();
		String getEvent = "FROM Event E WHERE E.id =" + eventId;
		Query getEventQuery = session.createQuery(getEvent);
		
		return (Event) getEventQuery.list().get(0);
	}

	/*

	@Override
	public List<String> getEventAttendees(Event event) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean inviteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean uninviteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
}
