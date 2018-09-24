package com.wander.notes.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import com.wander.notes.NotesApplication;


/**
 * Test cases for login controller class.
 *
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = NotesApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class LoginControllerTest {
	
	private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    
    /**
     * Basic setup.
     */
    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .setViewResolvers(viewResolver).build();
    }
    
    /**
     * Test case for login method.
     *
     * @throws Exception the default exception
     */
    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(get("/login")).andExpect(view().name("login"));
    }
}
