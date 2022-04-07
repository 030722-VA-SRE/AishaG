package com.revature.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.revature.controllers.TdController;
import com.revature.dto.TDto;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.TdModels;

@Component
public class TdModelAssembler implements RepresentationModelAssembler<TDto,
EntityModel<TDto>> {
	
	@Override
	public EntityModel<TDto> toModel(TDto tdto){
		
		try {
			return EntityModel.of(tdto,

				linkTo(methodOn(TdController.class).getModelById(tdto.getModelId())).withSelfRel(),
					linkTo(methodOn(TdController.class).getAllModels()).withRel("employees"));
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EntityModel<TdModels> toModel(TdModels tdmodel) throws ItemNotFoundException {
		
		return EntityModel.of(tdmodel,

			linkTo(methodOn(TdController.class).getModelById(tdmodel.getModelId())).withSelfRel(),
				linkTo(methodOn(TdController.class).getAllModels()).withRel("employees"));
	}
	
}