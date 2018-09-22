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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The user model
 *
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auth_user")
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
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
