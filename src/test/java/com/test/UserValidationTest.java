package com.test;


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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.psl.pluggin.controller.UserController;
import com.psl.pluggin.service.RepositoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:test-context.xml"})
public class UserValidationTest {
	
	@InjectMocks
    private UserController userController;
	
	 private MockMvc mockMvc;
	 
	 @Spy
	    private RepositoryService userService;
	 
	 private static MockHttpServletRequest request;
	    private static MockHttpServletResponse response;
	 
	 @Before
	    public void setup() {

	        // this must be called for the @Mock annotations above to be processed
	        // and for the mock service to be injected into the controller under
	        // test.
		 request = new MockHttpServletRequest();
         response = new MockHttpServletResponse();
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	    }
	 
	 @Test
	    public void testValidate() throws Exception {

	        String userName="vishal";
	        String password="testing123";

	        mockMvc.perform(
	                post("/validate").param("username",userName).param("password", password))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("userName", is(userName)));
	    }

}
