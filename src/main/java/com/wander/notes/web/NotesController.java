package com.wander.notes.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wander.notes.exceptions.UnauthorizedException;
import com.wander.notes.model.Notes;
import com.wander.notes.service.NotesService;
import com.wander.notes.service.UserService;

@RestController
public class NotesController {

	@Autowired
	NotesService notesService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/notes", method = RequestMethod.GET)
	public ModelAndView getAllNotes(){
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("notesList", notesService.findAll());
	    modelAndView.setViewName("notes");
	    return modelAndView;
	}
	
	@RequestMapping(value={"/notes/new", "/notes/{id}"}, method = RequestMethod.GET)
    public ModelAndView notesEditForm(@PathVariable(required = false, name = "id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != id) {
			try {
				modelAndView.addObject("notes", notesService.findById(id));
			} catch (UnauthorizedException e) {
				modelAndView.setViewName("403");
				return modelAndView;
			}
        } else {
        	modelAndView.addObject("notes", new Notes());
        }
		modelAndView.setViewName("notes-edit");
		return modelAndView;
    }

    @RequestMapping(value="/notes", method = RequestMethod.POST)
    public ModelAndView editNote(@Valid Notes notes, BindingResult bindingResult) {
    	ModelAndView modelAndView = new ModelAndView();
    	
    	if (bindingResult.hasErrors()) {
    		modelAndView.setViewName("notes-edit");
	    } else {
	        notesService.saveNotes(notes);
	        modelAndView.addObject("successMessage", "Notes saved successfully");
	        modelAndView.addObject("notesList", notesService.findAll());
	        modelAndView.setViewName("notes");
	    }    
        return modelAndView;
    }

    @RequestMapping(value="/notes/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteNote(@PathVariable(required = true, name = "id") Long id) {
    	ModelAndView modelAndView = new ModelAndView();
    	try {
			notesService.delete(id);
		} catch (UnauthorizedException e) {
			modelAndView.setViewName("403");
			return modelAndView;
		}
        modelAndView.addObject("successMessage", "Notes deleted successfully");
        modelAndView.addObject("notesList", notesService.findAll());
        modelAndView.setViewName("notes");
        return modelAndView;
    }
}
