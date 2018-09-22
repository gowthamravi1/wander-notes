package com.wander.notes.repository;

import java.util.ArrayList;

import org.junit.Assert;
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

/**
 * Test class for User Repository.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RepositoryConfiguration.class)
@ActiveProfiles("test")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:createUser.sql")
@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
        scripts = "classpath:deleteUser.sql")
public class UserRepositoryTest {

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
        Assert.assertEquals(getUser, user);
    }

    /**
     * This method is used to test the update method.
     */
    @Test
    public void updateTest() {
        User getUser = getUser();
        getUser.setPassword("userPassword");
        user = userRepository.save(getUser);
        Assert.assertEquals(getUser.getPassword(), user.getPassword());
    }

    /**
     * This method is used to test the find method.
     */
    @Test
    public void findTest() {
        User getUser = getUser();
        getUser.setUsername("user");
        save(getUser);
        User userByName = userRepository.findFirstByUsername(getUser.getUsername());
        user = userRepository.findOne(userByName.getId());
        Assert.assertEquals(getUser.getUsername(), user.getUsername());
    }

    /**
     * This method is used to test the findByUsername method.
     */
    @Test
    public void findByEmail() {
        User getUser = getUser();
        getUser.setEmail("test@test.com");
        save(getUser);
        user = userRepository.findByEmail(getUser.getEmail());
        assertEquals(getUser.getEmail(), user.getEmail());
    }

    /**
     * This method is used to test the delete method.
     */
    @Test
    public void deleteTest() {
        User getUser = getUser();
        getUser.setUsername("deleteAdmin");
        getUser.setRole("deleteUser");
        save(getUser);
        userRepository.delete(getUser);
        User deleteUser = userRepository
                .findFirstByUsername(getUser.getUsername());
        Assert.assertNull(deleteUser);
    }

    /**
     * This method is used to test the deleteById method.
     */
    @Test
    public void deleteByIdTest() {
        User getUser = getUser();
        getUser.setUsername("deleteAdminId");
        getUser.setRole("deleteUserId");
        save(getUser);
        User findUser = userRepository
                .findFirstByUsername(getUser.getUsername());
        userRepository.delete(findUser.getId());
        Assert.assertNull(userRepository.findOne(findUser.getId()));
    }

    /**
     * Save the user details.
     *
     * @param user the user.
     */
    private void save(User user) {
        userRepository.save(user);
    }

    /**
     * This method is used to test the findAll method.
     */
    @Test
    public void findAllTest() {
        try {
            Iterable<User> allUsers = getAllUsers();
            for (User user : allUsers) {
                save(user);
            }
            Assert.assertTrue(userRepository.findAll().iterator().hasNext());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * create new user and returned it.
     *
     * @return the user.
     */
    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("adminTest");
        user.setPassword("passwordTest");
        user.setEnabled(true);
        user.setRole("ADMIN");
        return user;
    }

    /**
     * create list of users and returned it.
     *
     * @return the array list of users.
     */
    private Iterable<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        user = new User("admin1", "password1", true, "admin1");
        allUsers.add(user);

        user = new User("admin2", "password2", true, "admin2");
        allUsers.add(user);
        return allUsers;
    }
}
