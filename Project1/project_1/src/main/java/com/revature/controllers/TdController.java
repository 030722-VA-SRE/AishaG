package com.revature.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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

import com.revature.assembler.TdModelAssembler;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.TdModels;
import com.revature.services.LoginService;
import com.revature.services.TdService;


@RestController
@RequestMapping("/tdmodels")
public class TdController {
	
	private TdService ts;
	private LoginService ls;
	private TdModelAssembler tma;
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public TdController(TdService ts) {
		super();
		this.ts = ts;
	}
	
	@GetMapping
	public ResponseEntity<List<TdModels>> getAllModels() {
		return new ResponseEntity<>(ts.getAllModels(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public EntityModel<TdModels> getModelById(@PathVariable("id") int id) throws ItemNotFoundException{
		if(id < 0) {
			throw ItemNotFoundException.createWith(id);
		}
		return tma.toModel(ts.getModelById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TdModels> updateModel(@PathVariable("id") int id, @RequestBody TdModels tdmodel) throws ItemNotFoundException {
		
		if(id < -1 || tdmodel == null) {
			throw ItemNotFoundException.createWith(id);
		}
			tdmodel.setModelId(id);
			return new ResponseEntity<>(ts.updateModel(tdmodel), HttpStatus.ACCEPTED);
		}
	//"Model " + tdmodel.getModelName() + "has been updated"
	
	@PostMapping
	public ResponseEntity<TdModels> addTdModel(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody TdModels newTdModel){
		
		ls.verifiedCreator(token);
		
		TdModels t = ts.addModel(newTdModel);
		return new ResponseEntity<>(newTdModel, HttpStatus.CREATED);
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteModelById(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id) throws ItemNotFoundException{

		ls.verifiedCreator(token);
		
		if(id <= 0) {
			throw ItemNotFoundException.createWith(id);
		}
			ts.deleteModelById(id);
			return new ResponseEntity<>("Model was deleted", HttpStatus.OK);
	}
}