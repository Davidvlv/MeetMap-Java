package au.usyd.elec5619.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import au.usyd.elec5619.domain.Message;
import au.usyd.elec5619.domain.User;


@Service(value = "messageService")
@Transactional
public class SimpleMessageService implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public void save(Message message) {
		this.messageDao.save(message);
	}

	@Override
	public List<Message> getInboxByUsername(String username) {
	    return messageDao.getInboxByUsername(username);
	}

	@Override
	public List<Message> getOutboxByUsername(String username) {
	    return messageDao.getOutboxByUsername(username);
	}

	@Override
	public void delete(Message message) {
		messageDao.delete(message);
	}

	@Override
	public String getLoggedInUsername(Model model) {
		return model.asMap().get("username").toString();
	}

	@Override
	public Message getMessageById(long id) {
		return messageDao.getMessageById(id);
	}

}
