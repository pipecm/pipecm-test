package ca.pipecm.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ca.pipecm.rest.dto.User;
import ca.pipecm.rest.repo.UserRepository;

@Controller  
@RequestMapping(path="/users") 
public class MainController {
	
	@Autowired            
	private UserRepository userRepository;

	@PostMapping(path="/addUser") 
	public @ResponseBody ResponseEntity<User> addNewUser(@RequestBody User user) {
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping(path="/allUsers")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/getUser/{id}")
	public @ResponseBody ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		return new ResponseEntity<User>(userRepository.findOne(id), HttpStatus.OK);
	}
	
	@PutMapping(path="/updateUser")
	public @ResponseBody ResponseEntity<User> updateUser(@RequestBody User user) {	
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteUser/{id}")
	public @ResponseBody ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) {
		User user = userRepository.findOne(id);
		userRepository.delete(user);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
}