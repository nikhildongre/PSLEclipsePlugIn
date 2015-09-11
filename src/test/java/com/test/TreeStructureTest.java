package com.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.psl.pluggin.controller.UserController;
import com.psl.pluggin.dao.AuditDAO;
import com.psl.pluggin.model.RepositoryFile;
import com.psl.pluggin.model.User;
import com.psl.pluggin.service.RepositoryService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:test-context.xml"})
public class TreeStructureTest
{
	@InjectMocks
	 private UserController userController;
	   
	private MockMvc mockMvc;
	    
	    @Spy
	    private RepositoryService userService;
	    
	    
	    @Mock
	    AuditDAO mockAuditDAO ;
	    
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

    @Ignore
    public void testValidate()
        throws Exception
    {
        String userName = "vishalgupta12";
        String password = "testing123";
        String url="https://github.com/nikhildongre/PSLEclipsePlugIn";
       // Mockito.doNothing().when(mockAuditDAO).save(isA(Audit.class));
        mockMvc.perform(post("/getRepostiorySubTree").param("username",  userName
        ).param("password", password).param("url", url)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("userName", Matchers.is(userName))).andExpect(MockMvcResultMatchers.jsonPath("authorised", Matchers.is(Boolean.valueOf(true))));
    }
    
    @Test
    public void testRepoTree()
        throws Exception
    {
        String userName = "vishalgupta12";
        String password = "testing123";
        String url="https://github.com/nikhildongre/PSLEclipsePlugIn";
        List<RepositoryFile>  repositoryFiles = new ArrayList<RepositoryFile>();
        
       
    	/*repositoryFiles.add(new RepositoryFile("Documents","https://github.com/nikhildongre/PSLEclipsePlugIn/tree/master/Documents","DIRECTORY"));
    	repositoryFiles.add(new RepositoryFile("README.md","https://github.com/nikhildongre/PSLEclipsePlugIn/blob/master/README.md","FILE"));
    	repositoryFiles.add(new RepositoryFile("pom.xml","https://github.com/nikhildongre/PSLEclipsePlugIn/blob/master/pom.xml","FILE"));
    	repositoryFiles.add(new RepositoryFile("src","https://github.com/nikhildongre/PSLEclipsePlugIn/tree/master/src","DIRECTORY"));
    	repositoryFiles.add(new RepositoryFile("target","https://github.com/nikhildongre/PSLEclipsePlugIn/tree/master/target","DIRECTORY"));
    
        //repositoryFiles.add(arg0);
        
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setRepositoryFiles(repositoryFiles);*/
        mockMvc.perform(post("/getRepostiorySubTree").param("username",  userName
        ).param("password", password).param("url", url)).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("userName", Matchers.is(userName)))
        .andExpect(MockMvcResultMatchers.jsonPath("repositoryFiles", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.repositoryFiles[0].fileName",Matchers.is("Documents")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.repositoryFiles[1].fileName",Matchers.is("README.md")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.repositoryFiles[2].fileName",Matchers.is("pom.xml")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.repositoryFiles[3].fileName",Matchers.is("src")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.repositoryFiles[4].fileName",Matchers.is("target")));
    }

   
}
