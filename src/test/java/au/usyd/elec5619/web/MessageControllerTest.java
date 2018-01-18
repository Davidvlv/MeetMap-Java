package au.usyd.elec5619.web;

import static org.junit.Assert.assertEquals;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import au.usyd.elec5619.domain.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageControllerTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter;


	@Autowired
	private MessageController messageController;
	
	@Before
	public void setup(){
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handlerAdapter = new AnnotationMethodHandlerAdapter();
	}

//	@Test
//	public void getMessage() throws Exception {
//		request.setRequestURI("/message");
//		request.setMethod("GET");
//		System.out.println(messageController);
//		final ModelAndView mav = handlerAdapter.handle(request, response, messageController);
//		System.out.println();
//		System.out.println(mav.getViewName());
//		System.out.println();
//		assertEquals("message", mav.getViewName());
//	}
//	
//	@Test
//	public void postMessage() throws Exception {
//		request.setRequestURI("/message");
//		request.setMethod("POST");
//        request.setParameter("recipient", "test");
//        request.setParameter("sender", "test2");
//        request.setParameter("subject", "testsub");
//        request.setParameter("messageBody", "testbody hello");
//        System.out.println(messageController);
//
//		final ModelAndView mav = handlerAdapter.handle(request, response, messageController);
//		Message submittedMessage = (Message) mav.getModelMap().get("messageForm");
//		assertEquals(submittedMessage.getRecipient(), "test");
//		assertEquals(submittedMessage.getSender(), "test2");
//		assertEquals(submittedMessage.getSubject(), "testsub");
//		assertEquals(submittedMessage.getMessageBody(), "testbody hello");
//	}
//	
//	@Test
//	public void getInbox() throws Exception {
//		request.setRequestURI("/inbox");
//		request.setMethod("GET");
//		final ModelAndView mav = handlerAdapter.handle(request, response, messageController);
//
//		assertEquals("inbox", mav.getViewName());
//	}
//	
//	@Test
//	public void getOutbox() throws Exception {
//		request.setRequestURI("/outbox");
//		request.setMethod("GET");
//		final ModelAndView mav = handlerAdapter.handle(request, response, messageController);
//
//		assertEquals("outbox", mav.getViewName());
//	}
	

	@Test
	public void getMessageDetail() throws Exception {
		request.setRequestURI("/message/1");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, messageController);

		assertEquals("messageDetail", mav.getViewName());
	}
	
	@Test
	public void testNull(){
		
	}
}