package au.usyd.elec5619.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest{
	private Event event = new Event();
	
	@Test
	public void testGetAndSetDescription(){
		assertNull(event.getDescription());
		String testDescription = "This is a test description";
		event.setDescription(testDescription);		
		assertEquals(event.getDescription(), testDescription);
	}
	
	@Test
	public void testGetAndSetEventName(){
		
	}

}
