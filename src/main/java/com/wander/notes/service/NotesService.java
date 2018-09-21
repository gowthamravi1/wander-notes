package com.wander.notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.wander.notes.repository.NotesRepository;

@Service
@Component
public class NotesService {

	@Autowired
	NotesRepository notesRepository;
}
