package com.test;

import com.psl.pluggin.controller.UserController;
import com.psl.pluggin.service.RepositoryService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:test-context.xml"})
public class UserValidationTest
{
	@InjectMocks
	 private UserController userController;
	   
	private MockMvc mockMvc;
	    
	    @Spy
	    private RepositoryService userService;
	    
	    private static MockHttpServletRequest request;
	    private static MockHttpServletResponse response;

  

    @Before
    public void setup()
    {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setHandlerExceptionResolvers(new ExceptionHandlerExceptionResolver()).build();
    }

    @Test
    public void testValidate()
        throws Exception
    {
        String userName = "vishalgupta12";
        String password = "testing123";
        String url="https://github.com/nikhildongre/PSLEclipsePlugIn";
        System.out.println(mockMvc);
        mockMvc.perform(post("/validate").param("username",  userName
        ).param("password", password).param("url", url)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("userName", Matchers.is(userName))).andExpect(MockMvcResultMatchers.jsonPath("authorised", Matchers.is(Boolean.valueOf(true))));
    }

   
}
