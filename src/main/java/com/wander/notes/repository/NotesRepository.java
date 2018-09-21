package com.wander.notes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wander.notes.model.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

	public Page<Notes> findAllByUserId(Long postId, Pageable pageable);
	
}
