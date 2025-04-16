package com.demo.controller;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Usercontroller {
	
	@Autowired
    private UserRepository userRepository;
	
	  @PostMapping("/signup")
	    public String signup(@RequestBody User user) {
	        if (userRepository.findByEmail(user.getEmail()) != null) {
	            return "Email already exists!";
	        }

	        if (user.getRole() == null || user.getRole().isEmpty()) {
	            user.setRole("USER"); // Default role
	        }

	        userRepository.save(user);
	        return "Signup successful!";
	    }

	    @PostMapping("/login")
	    public String login(@RequestBody User user) {
	        User existing = userRepository.findByEmail(user.getEmail());
	        if (existing != null && existing.getPassword().equals(user.getPassword())) {
	            return "Login successful! " + " Username: " + existing.getUsername() +  " " + "Role: " + existing.getRole() ;
	        }
	        return "Invalid email or password!";
	    }
	 
	    
	    // it handles in fronted when role is admin then returns the all users 
	    @GetMapping
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	    
	    
	    

}
