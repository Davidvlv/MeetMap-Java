package au.usyd.elec5619.web;

import java.util.List;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import au.usyd.elec5619.domain.UserGroup;
import au.usyd.elec5619.service.GroupService;

@Controller
public class GroupsRESTController {

	@Resource(name = "groupService")
	private GroupService groupService;
	
	/**
	 * REST : Returns the number of groups the user owns
	 */
	@RequestMapping(value = "/rest/groups/count", method = RequestMethod.GET)
	@ResponseBody 
	public String getOwnedGroupsCount(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<UserGroup> ownedGroups = this.groupService.getOwnedGroups(username);
		List<UserGroup> groupsBelongedTo = this.groupService.getGroupsBelongedTo(username);
		
		return "{\"count\" : "+ownedGroups.size()+"}";
	}
	
	/**
	 * REST : Get all owned groups
	 */
	@RequestMapping(value = "/rest/groups/", method = RequestMethod.GET)
	@ResponseBody 
	public String getAllOwnedGroups() throws JsonProcessingException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<UserGroup> ownedGroups = this.groupService.getOwnedGroups(username);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ownedGroups);
		
		return jsonInString;
	}
	
	/**
	 * REST : Deletes all groups owned by the user
	 */
	@RequestMapping(value = "/rest/groups/", method = RequestMethod.DELETE)
	@ResponseBody 
	public String deleteAllGroups(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			groupService.deleteAllGroups(username);
			return "{\"result\" : "+true+"}";
		} catch (HibernateException ex) {
			return "{\"result\" : "+false+"}";
		}
	}
	
	/**
	 * REST : Get a specific group
	 */
	@RequestMapping(value = "/rest/groups/{id}", method = RequestMethod.GET)
	@ResponseBody 
	public String getGroup(@PathVariable("id") Long id) throws JsonProcessingException {
		UserGroup group = groupService.getGroupById(id);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(group);
		
		return jsonInString;
	}
	
	/**
	 * REST : Delete a specific group
	 */
	@RequestMapping(value = "/rest/groups/{id}", method = RequestMethod.DELETE)
	@ResponseBody 
	public String deleteGroup(@PathVariable("id") Long id){
		try {
			groupService.deleteGroup(id);
			return "{\"result\" : "+true+"}";
		} catch (HibernateException ex) {
			return "{\"result\" : "+false+"}";
		}
	}
}
