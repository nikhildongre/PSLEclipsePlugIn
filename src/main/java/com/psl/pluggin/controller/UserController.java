package com.psl.pluggin.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

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
		logger.info("Username:"+userName+" Password:"+password);
		User user=new User();
		user.setUserName(userName);
		user.setUrl(url);
		Audit ad=new Audit();
		ad.setUname(userName);
		ad.setPwd(password);
		ad.setUrl(url);
		ad.setCreatedDate(new Date());
		ad.setUpdatedDate(new Date());
		auditDAO.save(ad);
	
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
	
    @RequestMapping(value = "/getRepostiorySubTree", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON)
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
