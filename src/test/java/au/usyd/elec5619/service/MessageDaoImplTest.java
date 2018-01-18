package au.usyd.elec5619.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.usyd.elec5619.domain.Message;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoImplTest {
	
	@Autowired
	private MessageDaoImpl messageDao;
	
	@Test
	public void testGetInbox(){
		Message message = new Message();
		message.setSender("user1");
		message.setRecipient("user2");
		message.setSubject("hi");
		message.setMessageBody("Hello user 2");
		messageDao.save(message);
		
		List<Message> msgList = messageDao.getInboxByUsername("user2");
		int length = msgList.size();
		Message retrieved_message = msgList.get(length-1);
		
		assertEquals("user2", retrieved_message.getRecipient());
		assertEquals("user1", retrieved_message.getSender());
		assertEquals("hi", retrieved_message.getSubject());
		assertEquals("Hello user 2", retrieved_message.getMessageBody());
		
		messageDao.delete(message);
	}
	
	@Test
	public void testGetOutbox(){
		Message message = new Message();
		message.setSender("user1");
		message.setRecipient("user2");
		message.setSubject("hi");
		message.setMessageBody("Hello user 2");
		messageDao.save(message);
		
		List<Message> msgList = messageDao.getOutboxByUsername("user1");
		int length = msgList.size();
		Message retrieved_message = msgList.get(length-1);
		
		assertEquals("user2", retrieved_message.getRecipient());
		assertEquals("user1", retrieved_message.getSender());
		assertEquals("hi", retrieved_message.getSubject());
		assertEquals("Hello user 2", retrieved_message.getMessageBody());
		
		messageDao.delete(message);
	}

}