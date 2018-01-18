package au.usyd.elec5619.service;


import java.util.List;

import au.usyd.elec5619.domain.Message;

public interface MessageDao {
	
	public void save(Message message);
	
	public Message getMessageById(long id);
	
	public List<Message> getInboxByUsername(String username);
	
	public List<Message> getOutboxByUsername(String username);
	
	public void delete(Message message);
}
