package com.wander.notes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

/**
 * The Notes data model
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "notes")
public class Notes extends AuditModel {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "note_id")
	    private Integer id;
		
		@Column(name = "title")
		@Length(min = 5, message = "*Your title must have at least 5 characters")
	    @NotEmpty(message = "*Please provide your title")
		private String title;
		
		@Column(columnDefinition = "TEXT")
		private String description; 
		
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "user_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
		private User user;
}
