package com.psl.pluggin.controller;

import java.io.IOException;
<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> branch 'master' of https://github.com/nikhildongre/PSLEclipsePlugIn

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import javax.ws.rs.core.MediaType;
=======
>>>>>>> branch 'master' of https://github.com/nikhildongre/PSLEclipsePlugIn

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.psl.pluggin.dao.AuditDAO;
import com.psl.pluggin.dao.AuditDAOImpl;
import com.psl.pluggin.model.Audit;
import com.psl.pluggin.model.User;
import com.psl.pluggin.service.RepositoryService;

/**
 * @author vishal_gupta1
 * 
 */

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	public RepositoryService repositoryService;
<<<<<<< HEAD
	
	@Autowired
	@Qualifier("auditDao")
	 AuditDAO auditDAO;
		
		
		Audit ad=new Audit();
=======
>>>>>>> branch 'master' of https://github.com/nikhildongre/PSLEclipsePlugIn

	/**
	 * Method validates user credentials
	 * 
	 */
<<<<<<< HEAD
	@RequestMapping(value = "/validate", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON)
=======
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
>>>>>>> branch 'master' of https://github.com/nikhildongre/PSLEclipsePlugIn
	@ResponseBody
	public ResponseEntity<User> validateUser(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String url = request.getParameter("url");
		logger.info("Username:" + userName + " url:" + url);
		User user = new User();
		user.setUserName(userName);
		user.setUrl(url);
		user.setPassword(password);
<<<<<<< HEAD
		ad.setUname(userName);
		ad.setPwd(password);
		ad.setUrl(url);
		ad.setCreatedDate(new Date());
		ad.setUpdatedDate(new Date());
		System.out.println("Audit:"+ad);
		auditDAO.save(ad);
=======
>>>>>>> branch 'master' of https://github.com/nikhildongre/PSLEclipsePlugIn
		try {
			if (repositoryService.authenticate(userName, password)) {
				user.setAuthorised(true);
				repositoryService.getTreeStructure(user);
			} else {
				user.setAuthorised(false);
			}
		} catch (IOException e) {
			user.setAuthorised(false);
			logger.info("Exception in validating User  :" + e.getMessage());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRepostiorySubTree", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> getRepostiorySubTree(
			HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String url = request.getParameter("url");
		logger.info("Username:" + userName + " url:" + url);
		User user = new User();
		user.setUserName(userName);
		user.setUrl(url);
		user.setPassword(password);
		repositoryService.getTreeStructure(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
