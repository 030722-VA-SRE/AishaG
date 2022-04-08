package com.revature.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
import com.revature.dto.TDto;
import com.revature.dto.UserDto;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.TdModels;
import com.revature.repositories.UserRepository;
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
	public TdController(TdService ts, LoginService ls, TdModelAssembler tma, UserRepository ur) {
		super();
		this.ts = ts;
		this.ls = ls;
		this.tma = tma;

	}
	
	@GetMapping
	public CollectionModel<EntityModel<TDto>> getAllModels() {
		List<EntityModel<TDto>> tdto = 
				ts.getAllModels().stream()
				.map(tma::toModel)
			.collect(Collectors.toList());
		
		return CollectionModel.of(tdto, 
		linkTo(methodOn(TdController.class).getAllModels()).withSelfRel());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<TDto>> getModelById(@PathVariable("id") int id) {
	
		TDto td = ts.getModelById(id);
		
		EntityModel<TDto> entitymodel =
		tma.toModel(td);
		
		return ResponseEntity
				.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entitymodel);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EntityModel<TdModels>> updateModel(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id, @RequestBody TdModels updatedModel) throws ItemNotFoundException {
		ls.verifiedAdmin(token);
		
		TdModels tdmodel = ts.updateModel(updatedModel, id);
		
		tdmodel.setModelName(updatedModel.getModelName());
		tdmodel.setDescription(updatedModel.getDescription());
		tdmodel.setPrice(updatedModel.getPrice());

			
		EntityModel<TdModels> entitymodel =
				tma.toModel(updatedModel);
						
						ResponseEntity.ok(updatedModel);
						
						return ResponseEntity
				.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
						.body(entitymodel);
		}

	
	@PostMapping
	public ResponseEntity<EntityModel<TdModels>> addTdModel(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody TdModels newTdModel){
		
		ls.verifiedCreator(token);
		
		TdModels tdmodel = ts.addModel(newTdModel);

		LOG.trace("a new model has been added! Ensure it is properly setup please.");	
		
			EntityModel<TdModels> entitymodel =
		tma.toModel(tdmodel);
			
			return ResponseEntity
					
	.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
		.body(entitymodel);
		
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteModelById(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id) throws ItemNotFoundException{

		ls.verifiedCreator(token);
		
		if(id <= 0) {
			throw new ItemNotFoundException(id);
		}
			ts.deleteModelById(id);
			LOG.info("model has been deleted, please verify token of previous verfiedCreator token for more details.");
			return new ResponseEntity<>("Model was deleted", HttpStatus.OK);
	}
}