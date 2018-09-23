package com.wander.notes.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.wander.notes.NotesApplication;
import com.wander.notes.model.Notes;
import com.wander.notes.service.NotesService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = NotesApplication.class)
@ActiveProfiles("test")
@WithMockUser
public class NotesControllerTest {
	
	private MockMvc mockMvc;

    @InjectMocks
    private NotesController notesController;

    @Mock
    private HttpSession session;
    
    @Mock
	NotesService notesService;

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
        List<Notes> notesList = new ArrayList<Notes>();
        mockMvc = MockMvcBuilders.standaloneSetup(notesController)
                .setViewResolvers(viewResolver).build();
        
        Mockito.when(notesService.findAll()).thenReturn(notesList);
    }
    
    /**
     * Test case get all notes method.
     *
     * @throws Exception the default exception
     */
    @Test
    public void getNotes() throws Exception {
        mockMvc.perform(get("/notes")).andExpect(view().name("notes"));
    }
}
