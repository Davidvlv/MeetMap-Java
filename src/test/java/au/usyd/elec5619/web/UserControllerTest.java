package au.usyd.elec5619.web;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import au.usyd.elec5619.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HandlerAdapter handlerAdapter;

	@Autowired
	private UserController userController;
	
	@Before
	public void setup(){
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handlerAdapter = new AnnotationMethodHandlerAdapter();
	}

	@Test
	public void getRegister() throws Exception {
		request.setRequestURI("/register");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, userController);

		assertEquals("register", mav.getViewName());
	}
	
	@Test
	public void postRegister() throws Exception {
		request.setRequestURI("/register");
		request.setMethod("POST");
        request.setParameter("username", "userxyz");
        request.setParameter("password", "password");
        request.setParameter("emailAddress", "mail@mail");

		final ModelAndView mav = handlerAdapter.handle(request, response, userController);
		User registeredUser = (User) mav.getModelMap().get("userForm");
		assertEquals(registeredUser.getUsername(), "userxyz");
	}
	
	@Test
	public void getHome() throws Exception {
		request.setRequestURI("/");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, userController);

		assertEquals("redirect:/home", mav.getViewName());
	}
	
	@Test
	public void getLogout() throws Exception {
		request.setRequestURI("/logout");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, userController);

		assertEquals("redirect:/login?logout", mav.getViewName());
	}
	

	@Test
	public void getLogin() throws Exception {
		request.setRequestURI("/login");
		request.setMethod("GET");
		final ModelAndView mav = handlerAdapter.handle(request, response, userController);

		assertEquals("login", mav.getViewName());
	}
}
