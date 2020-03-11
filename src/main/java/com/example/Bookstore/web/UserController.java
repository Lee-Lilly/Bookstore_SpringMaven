package com.example.Bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.Bookstore.domain.SignupForm;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;



@Controller
public class UserController {
	@Autowired
    private UserRepository userRepository; 

	
	@RequestMapping(value = "signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
	
	 /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
	
	 @PostMapping(value = "saveUser")
	 public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
	    		//BCryptPasswordEncoder() transform plain text to hashed password
		    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    	String hashPwd = encoder.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setName(signupForm.getUserName());
		    	newUser.setEmail(signupForm.getEmail());
		    	newUser.setRole(signupForm.getRole());
		    
		    	
		    	if (userRepository.findByName(signupForm.getUserName()) == null) { // Check if user exists
		    		userRepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }  
	 
}
	
