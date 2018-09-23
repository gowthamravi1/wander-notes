package com.wander.notes.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wander.notes.model.Role;
import com.wander.notes.model.User;
import com.wander.notes.repository.RoleRepository;
import com.wander.notes.repository.UserRepository;

@Service
public class UserService {
	
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
	    this.userRepository = userRepository;
	    this.roleRepository = roleRepository;
	    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
    	user.setEnabled(true);
    	Role userRole = roleRepository.findByRole("USER");
    	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
	

}
