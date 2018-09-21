package com.wander.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wander.notes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	/**
	 * Find the user by email
	 * 
	 * @param email the email
	 * @return the user
	 */
	public User findByEmail(String email);

}
