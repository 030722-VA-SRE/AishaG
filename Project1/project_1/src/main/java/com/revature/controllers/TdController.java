package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.TdModels;
import com.revature.services.TdService;


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
	public ResponseEntity<List<TdModels>> getAll() {
		return new ResponseEntity<>(ts.getAllModels(), HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<TdModels> getModelById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(ts.getModelById(id), HttpStatus.OK);
		} catch (ItemNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/updateModel/{id}")
	public ResponseEntity<String> updateModel(@PathVariable("id") int id) {
		
		try {
			ts.updateModelById(id, null);
			TdModels tm = ts.getModelById(id);
			return new ResponseEntity<>("Model " + tm.getModelName() + "has been updated", HttpStatus.ACCEPTED);
		} catch (ItemNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@PostMapping("/addModel")
	public ResponseEntity<String> addTdModel(@RequestBody TdModels newTdModel, @RequestHeader String header){
		TdModels t = ts.addModel(newTdModel);
		return new ResponseEntity<>("Model " + t.getModelName() + "has been created", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteModel/{id}")
	public ResponseEntity<String> deleteModelById(@PathVariable("id") int id){
		try {
			ts.deleteModelById(id);
			return new ResponseEntity<>("Model was deleted", HttpStatus.OK);
		} catch (ItemNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}