package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.TdModels;
import com.revature.models.User;
import com.revature.services.TdService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/tdmodels")
public class TdController {
	
	private TdService ts;

	@Autowired
	public TdController(TdService ts) {
		super();
		this.ts = ts;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<>(ts.getAllModels(), HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<User> getModelById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(ts.getModelById(id), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	 
	@PostMapping
	public ResponseEntity<String> addTdModel(@RequestBody User user, @RequestHeader String header){
		TdModels t = ts.addTdModel(newTdModel);
		return new ResponseEntity<>("Model " + t.getModelName() + "has been created", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteModelById(@PathVariable("id") int id){
		try {
			us.deleteUser(id);
			return new ResponseEntity<>("User was deleted", HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}