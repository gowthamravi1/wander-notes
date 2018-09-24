package com.wander.notes.repository;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.wander.notes.RepositoryConfiguration;
import com.wander.notes.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@ActiveProfiles("test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
scripts = "classpath:createUser.sql")
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
scripts = "classpath:deleteUser.sql")
public class NotesRepositoryIntegrationTest {

	/**
     * User Repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * User.
     */
    private User user;

    /**
     * This method is used to test the save method.
     */
    @Test
    public void saveTest() {
        User getUser = getUser();
        user = userRepository.save(getUser);
        assertEquals(getUser.getEmail(), user.getEmail());
    }
    
    /**
     * create new user and returned it.
     *
     * @return the user.
     */
    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setEmail("test@test.com");
        user.setPassword("passwordTest");
        user.setEnabled(true);
        return user;
    }
}
