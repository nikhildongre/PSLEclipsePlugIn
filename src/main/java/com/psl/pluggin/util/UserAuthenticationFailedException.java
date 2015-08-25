package com.psl.pluggin.util;

/**
 * @author vishal_gupta1
 *
 */

public class UserAuthenticationFailedException extends RuntimeException{
	UserAuthenticationFailedException(String msg)
	{
		super(msg);
	}

}
