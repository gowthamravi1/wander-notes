package com.wander.notes.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Data
public abstract class AuditModel implements Serializable {

	
		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "created_at", nullable = false, updatable = false)
	    @CreatedDate
	    private Date createdAt;
	
	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "updated_at", nullable = false)
	    @LastModifiedDate
	    private Date updatedAt;  
}
