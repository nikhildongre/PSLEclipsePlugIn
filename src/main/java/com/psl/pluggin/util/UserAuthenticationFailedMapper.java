/**
 * 
 */
package com.psl.pluggin.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author vishal_gupta1
 *
 */
@Provider
public class UserAuthenticationFailedMapper implements ExceptionMapper<UserAuthenticationFailedException>{

	@Override
	public Response toResponse(UserAuthenticationFailedException ex) {
		return Response.status(Status.BAD_REQUEST)
	  			   .entity(ex.getMessage())
	  			   .build();
	}

}
