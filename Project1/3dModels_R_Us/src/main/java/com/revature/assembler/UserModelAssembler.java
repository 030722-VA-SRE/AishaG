package com.revature.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.revature.controllers.UserController;
import com.revature.dto.UserDto;
import com.revature.models.User;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto,
EntityModel<UserDto>> {
	
	@Override
	public EntityModel<UserDto> toModel(UserDto userdto) {
		
		return EntityModel.of(userdto,

			linkTo(methodOn(UserController.class).findUserById(userdto.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAllUsers()).withRel("employees"));
	}
	
	public EntityModel<User> toModel(User user) {
		
		return EntityModel.of(user,

			linkTo(methodOn(UserController.class).findUserById(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getAllUsers()).withRel("employees"));
	}
	
}