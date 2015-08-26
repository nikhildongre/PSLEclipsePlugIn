package com.psl.pluggin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public ResponseEntity<User> validateUser(HttpServletRequest request,
            HttpServletResponse response) {
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String url=request.getParameter("password");
		
		System.out.println("Username:"+userName+" Password:"+url);
		logger.info("Username:"+userName+" Password:"+password);
	User user=new User();
	user.setUserName(userName);
	user.setPassword(password);
	List<String> str=new ArrayList<String>();
	str.add("vishal");
	user.setAllowedurls(str);
	return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
