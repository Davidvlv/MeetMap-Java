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

import au.usyd.elec5619.domain.CreateGroupForm;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.domain.UserGroup;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CreateGroupControllerTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter;
	
	@Autowired
	private CreateGroupController createGroupController;
	
	@Before
	public void setup(){
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();

		handlerAdapter = new AnnotationMethodHandlerAdapter();
	}
	
	@Test
	public void getCreateGroup() throws Exception {
		// Send a GET request to the creategroup page
		request.setRequestURI("/creategroup");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, createGroupController);

		// Assert that the creategroup page was navigated to successfully
		assertEquals("creategroup", mav.getViewName());
	}
	
	@Test
	public void postCreateGroup() throws Exception {
		// Send a POST request to the creategroup page
		request.setRequestURI("/creategroup");
		request.setMethod("POST");
		CreateGroupForm form = new CreateGroupForm();
		form.setGroupName("post-create-test");
		form.setDescription("post-create-test");
		request.setAttribute("createGroupForm", form);
		createGroupController.setCurrentUser("post-create-test");
		final ModelAndView mav = handlerAdapter.handle(request, response, createGroupController);
		
		// Assert that the group was correctly created (view has changed to groups view)
		assertEquals("redirect:/groups", mav.getViewName());
	}
}
