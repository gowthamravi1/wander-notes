package com.wander.notes.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wander.notes.model.User;
import com.wander.notes.service.UserService;

@RestController(value="/signup")
public class RegistrationController {
	
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView registration(){
	    ModelAndView modelAndView = new ModelAndView();
	    User user = new User();
	    modelAndView.addObject("user", user);
	    modelAndView.setViewName("registration");
	    return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	    ModelAndView modelAndView = new ModelAndView();
	    User userExists = userService.findUserByEmail(user.getEmail());
	    if (userExists != null) {
	        bindingResult
	                .rejectValue("email", "error.user",
	                        "There is already a user registered with the email provided");
	    }
	    if (bindingResult.hasErrors()) {
	        modelAndView.setViewName("registration");
	    } else {
	        userService.saveUser(user);
	        modelAndView.addObject("successMessage", "User has been registered successfully");
	        modelAndView.addObject("user", new User());
	        modelAndView.setViewName("registration");
	
	    }
	    return modelAndView;
	}
}
