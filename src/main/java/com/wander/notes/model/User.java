package com.wander.notes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/**
 * The user model
 *
 */
@Entity
@Table(name = "user")
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "user_id")
	    private Integer id;
	    
	    @Column(name = "email", unique=true)
	    @Email(message = "*Please provide a valid Email")
	    @NotEmpty(message = "*Please provide an email")
	    private String email;
	    
	    @Column(name = "password")
	    @Length(min = 5, message = "*Your password must have at least 5 characters")
	    @NotEmpty(message = "*Please provide your password")
	    private String password;
	    
	    @Column(name = "name")
	    @NotEmpty(message = "*Please provide your name")
	    private String name;
	
}
