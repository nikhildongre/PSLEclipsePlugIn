package com.psl.pluggin.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public RepositoryService repositoryService;
	
	@Autowired 
	AuditDAO auditDAO;
	
	
	Audit ad=new Audit();
	
	
	
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
		String url=request.getParameter("url");
		
		System.out.println("Username:"+userName+" Password:"+url);
		logger.info("Username:"+userName+" Password:"+password);
	User user=new User();
	user.setUserName(userName);
	user.setUrl(url);
	
	ad.setUname(userName);
	ad.setPwd(password);
	ad.setUrl(url);
	ad.setCreatedDate(new Date());
	ad.setUpdatedDate(new Date());
	auditDAO.save(ad);
	
	try {
		user.setAuthorised(repositoryService.authenticate(userName, password));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
