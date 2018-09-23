package com.wander.notes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.wander.notes.model.Notes;
import com.wander.notes.model.User;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

	@Query("SELECT n FROM Notes n WHERE n.user=:user")
	public List<Notes> findAllByUser(@Param("user") User user);
	
}
