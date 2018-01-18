package au.usyd.elec5619.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.elec5619.domain.Message;

@Service(value = "messageDao")
@Transactional
public class MessageDaoImpl implements MessageDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void save(Message message) {
		this.sessionFactory.getCurrentSession().save(message);

	}

	@Override
	public Message getMessageById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Message fetchedMessage = (Message) currentSession.get(Message.class, id);
		return fetchedMessage;
	}

	@Override
	public List<Message> getInboxByUsername(String username) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		Query qry = session.createQuery("from Message m where m.recipient=:username");
		qry.setParameter("username", username);
		
	    return qry.list();
	}

	@Override
	public List<Message> getOutboxByUsername(String username) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		Query qry = session.createQuery("from Message m where m.sender=:username");
		qry.setParameter("username", username);
	    return qry.list();
	}

	@Override
	public void delete(Message message) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(message);

	}

}
