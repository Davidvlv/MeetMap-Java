package au.usyd.elec5619.domain;

import junit.framework.TestCase;

public class MessageTest extends TestCase {
	
	private Message message;
	
	protected void setUp() throws Exception {
		message = new Message();
	}
	
	public void testSetAndGetMessageBody(){
		String messageBody = "Hello my name is Tom";
		assertNull(message.getMessageBody());
		message.setMessageBody(messageBody);
		assertEquals(messageBody, message.getMessageBody());
	}
	
	public void testSetAndGetSender() {
		String sender = "tomdoml";
		message.setSender(sender);
		assertEquals(sender, message.getSender());
	}
	
	public void testSetAndGetRecipient() {
		String recipient = "tomdoml";
		message.setRecipient(recipient);
		assertEquals(recipient, message.getRecipient());
	}
	

	public void testSetAndGetSubject() {
		String subject = "hi";
		message.setSubject(subject);
		assertEquals(subject, message.getSubject());
	}

}
