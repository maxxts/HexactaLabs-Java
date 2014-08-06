package ar.com.hexacta.tpl.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.IUsersService;

@Service
public class UserWS {
	public UserWS(){
		
	}
	@Autowired
	private IUsersService userService;
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}
	
	@GET
	@Path("/{userId}")
	@Produces("application/json")
	public User findComment(@PathParam("userId") final String userId) {
		return userService.findUser(new Long(userId));
	}
	
	/* TODO: metodo para obtener usuario a partir de user+pass
	@GET
	@Path("/")
	@Consumes("application/j")
	*/
	public User validateUser(String username, String password){
		return userService.findUserByUsernameAndPassword(username, password);
	}
	
	
	@POST
	@Path("/")
	@Consumes("application/json")
	public Response createUser(
			@Multipart(value = "newUser", type = "application/json") final String jsonUser) {
		try {

			userService.createUser(parseUser(jsonUser));

		} catch (JsonParseException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();

	}
	
	@PUT
	@Path("/{userId}")
	@Consumes("application/json")
	public Response updateUser(
			@PathParam("userId") final String userId,
			final String jsonUser) {
		try {
			User user = parseUser(jsonUser);
			user.setId(new Long(userId));
			userService.updateUser(user);

		} catch (JsonParseException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteComment(@PathParam("userId") final String userId) {
		userService.deleteUserById(new Long(userId));
	}
	
	private User parseUser(final String jsonUser) throws JsonParseException, JsonMappingException, IOException {
		User newUser = new User();
		ObjectMapper mapper = new ObjectMapper();
		newUser = mapper.readValue(jsonUser, User.class);
		return newUser;
	}
	
	public IUsersService getUsersService() {
		return userService;
	}

	public void setUserService(final IUsersService userService) {
		this.userService = userService;
	}
}
