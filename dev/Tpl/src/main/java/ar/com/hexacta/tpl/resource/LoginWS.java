package ar.com.hexacta.tpl.resource;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.ILoginService;

@Service
public class LoginWS {
	
	@Autowired
	private ILoginService loginService;
	

	@GET
	@Path("/")
	@Produces("application/json")
	public User validateUser(@HeaderParam("Authorization") String auth){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decoded;
		try {
			decoded = decoder.decodeBuffer(auth);
			String alldecoded = new String (decoded);
			String basic = "basic ";
			String username = alldecoded.substring(basic.length(), alldecoded.indexOf(':'));
			//String password = alldecoded.substring (alldecoded.indexOf(':') + 1);
			return loginService.findUserByUsername(username);
		} catch (IOException e) {
			return null;
		}
		
	}
	
}
