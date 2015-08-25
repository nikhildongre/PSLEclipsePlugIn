package com.psl.pluggin.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.psl.pluggin.model.User;



/**
 * @author vishal_gupta1
 *
 */

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 *Method validates user credentials
	 *
	 */
	@RequestMapping(value="/validate",method = RequestMethod.POST)
	//@Consumes(value=MediaType.APPLICATION_JSON)
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response validateUser(@FormParam("username") String userName,
								@FormParam("password") String password) {
		System.out.println("Username:"+userName+" Password:"+password);
		logger.info("Username:"+userName+" Password:"+password);
	User user=new User();
	user.setUserName(userName);
	user.setPassword(password);
	return Response.ok(user).build();
	}

}
