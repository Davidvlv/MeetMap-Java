package au.usyd.elec5619.web;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import au.usyd.elec5619.domain.CreateEventForm;
import au.usyd.elec5619.domain.CreateGroupForm;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserGroup;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter;
	
	@Autowired
	private HomeController homeController;
	
	@Before
	public void setup(){
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();

		handlerAdapter = new AnnotationMethodHandlerAdapter();
	}
	
	@Test
	public void getHome() throws Exception {
		// Send a GET request to the home page
		request.setRequestURI("/home");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, homeController);

		// Assert that the home page was navigated to successfully
		assertEquals("home", mav.getViewName());
	}
	
	@Test
	public void postHome() throws Exception {
		// Send a POST request to the home page
		request.setRequestURI("/home");
		request.setMethod("POST");
		CreateEventForm form = new CreateEventForm();
		form.setEventName("post-home-test");
		form.setDescription("post-home-test");
		form.setEndTime("999999999999");
		form.setStartTime("111111111111");
		form.setIsPrivate(false);
		form.setLatitude("131");
		form.setLatitude("123");
		request.setAttribute("createEventForm", form);
		final ModelAndView mav = handlerAdapter.handle(request, response, homeController);
		
		// Assert that the group was correctly created (view has changed to groups view)
		assertEquals("redirect:/home", mav.getViewName());
	}
}
