package ar.com.hexacta.tpl.resource;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.ILoginService;

@Service
public class LoginWS {
	
	@Autowired
	private ILoginService loginService;
	
	public LoginWS(){}

	@GET
	@Path("/")
	@Produces("application/json")
	public User validateUser(@HeaderParam("Authentication") String auth){
		String basic = "basic ";
		String username = auth.substring(basic.length(), auth.indexOf(':'));
		String password = auth.substring (auth.indexOf(':') + 1);
		
		User user = loginService.findUserByUsername(username);
		if (user == null){ //there's no user with such username
			return null;
		}
		if (!user.getPassword().equals(password)){//the password is incorrect for that username
			return null;
		}
		return user;
	}
	
}
