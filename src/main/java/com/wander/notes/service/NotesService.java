package com.wander.notes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wander.notes.exceptions.UnauthorizedException;
import com.wander.notes.model.Notes;
import com.wander.notes.model.User;
import com.wander.notes.repository.NotesRepository;
import com.wander.notes.repository.UserRepository;

@Service
@Component
public class NotesService {

	NotesRepository notesRepository;
	
	UserRepository userRepository;
	
	@Autowired
	public NotesService(UserRepository userRepository, NotesRepository notesRepository) {
	    this.userRepository = userRepository;
	    this.notesRepository = notesRepository;
	}
	
	private User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserName = authentication.getName();
		User currentUser = userRepository.findByEmail(currentUserName);
		return currentUser;
	}
	
	public List<Notes> findAll() {
		User currentUser = getCurrentUser();
		return notesRepository.findAllByUser(currentUser);
	}
	
	public Notes findById(Long id) throws UnauthorizedException {
		User currentUser = getCurrentUser();
		Notes note = notesRepository.getOne(id);
		if(!currentUser.getId().equals(note.getUser().getId())) {
			throw new UnauthorizedException("Access Denied");
		}
		return note; 
	}
	
	public void delete(Long id) throws UnauthorizedException {
		User currentUser = getCurrentUser();
		Notes note = notesRepository.getOne(id);
		if(!currentUser.getId().equals(note.getUser().getId())) {
			throw new UnauthorizedException("Access Denied");
		}
		notesRepository.deleteById(id);
	}
	
	public Notes saveNotes(Notes notes) {
		User currentUser = getCurrentUser();
		notes.setUser(currentUser);
		return notesRepository.save(notes);
	}
}
