package com.wander.notes.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wander.notes.model.Notes;
import com.wander.notes.model.User;
import com.wander.notes.repository.NotesRepository;
import com.wander.notes.repository.UserRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WithMockUser
public class NotesServiceTest {

	
	@Mock
    private UserRepository mockUserRepository;
	
	@Mock
    private NotesRepository mockNotesRepository;
	
	private NotesService noteServiceUnderTest;
    private User user;
    private Notes note;
    
    @Before
    public void setUp() {
        initMocks(this);
        noteServiceUnderTest = new NotesService(mockUserRepository, mockNotesRepository);
        user = User.builder()
                .id((long) 1)
                .name("test")
                .email("test@test.com")
                .build();
        
        note = Notes.builder()
                .id((long) 1)
                .title("test123")
                .description("description")
                .user(user)
                .build();

        Mockito.when(mockUserRepository.save(any())).thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
        Mockito.when(mockNotesRepository.save(any())).thenReturn(note);
    }
    
    @Test
    public void saveNotes() {
        // Setup
        final String title = "test123";

        // Run the test
        Notes result = noteServiceUnderTest.saveNotes(Notes.builder().title(title).build());

        // Verify the results
        assertEquals(title, result.getTitle());
    }
    
    @Test
    public void deleteNotes() {
        // Setup
        final String title = "test123";

        // Run the test
        Notes result = noteServiceUnderTest.saveNotes(Notes.builder().build());

        // Verify the results
        assertEquals(title, result.getTitle());
    }
	
}
