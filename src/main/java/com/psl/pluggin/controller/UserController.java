package com.psl.pluggin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.pluggin.model.User;
import com.psl.pluggin.service.RepositoryService;



/**
 * @author vishal_gupta1
 *
 */

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public RepositoryService repositoryService;
	
	/**
	 *Method validates user credentials
	 *
	 */
	@RequestMapping(value="/validate",method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<User> validateUser(HttpServletRequest request,
            HttpServletResponse response) {
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String url=request.getParameter("url");
		
		System.out.println("Username:"+userName+" Password:"+password+" Url:"+url);
		logger.info("Username:"+userName+" Password:"+password);
	User user=new User();
	user.setUserName(userName);
	try {
		boolean isAuth=repositoryService.authenticate(userName, password);
		
		user.setAuthorised(isAuth);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
