package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.TDto;
import com.revature.dto.UserDto;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.TdModels;
import com.revature.models.User;
import com.revature.repositories.TdModelRepository;
import com.revature.repositories.UserRepository;

@Service
public class TdService{

	private TdModelRepository tr; //= new TdPostgres();
	private UserRepository ur;
	
	@Autowired
	public TdService(TdModelRepository tr, UserRepository ur) {
		super();
		this.ur = ur;
		this.tr = tr;
	}
	
	public TDto getModelById(int id) throws ItemNotFoundException{
		TdModels tdmodel = tr.getById(id);
		if(id < 0) {
		throw new ItemNotFoundException(id);
		}
		return new TDto(tdmodel);
	}
	@Transactional
	public TdModels addModel(TdModels newTdModel) {
		//implment logic to catch a ItemFoundException
		//Area to implement setting creator by token split value
		return tr.save(newTdModel);
	}
	
	public List<TdModels> getAllModels() {
//		List<TdModels> tdlist = tr.findAll();
//		return tdlist; 
		
		return tr.findAll();
	}
	
	@Transactional
	public TdModels updateModel(TdModels tdmodel, int id) throws ItemNotFoundException{
		TdModels updatedmodel = tr.getById(id);
		
			if (tdmodel.getModelName() != null && !tdmodel.getModelName().equals(updatedmodel.getModelName())) {
				updatedmodel.setModelName(tdmodel.getModelName());
			}
			if (tdmodel.getDescription() != null && !tdmodel.getDescription().equals(updatedmodel.getDescription())) {
				updatedmodel.setDescription(tdmodel.getDescription());
			}
			if (tdmodel.getPrice() != -1 && tdmodel.getPrice() != (updatedmodel.getPrice())) {
				updatedmodel.setPrice(tdmodel.getPrice());
			}
			if(id < -1 || tdmodel == null) {
				throw ItemNotFoundException.createWith(id);
			}
			tdmodel.setModelId(id);
		
		return tr.save(updatedmodel);
		
	}
	
	@Transactional
	public void deleteModelById(int id) throws ItemNotFoundException {
		TdModels tdmodel = tr.getById(id);
		tr.delete(tdmodel);
		//refactor to allow only admins to delete or for user to delete
	}
//
//	public List<TdModels> getModelByPrice(double price) {
//		return tr.findBy(price);
//	}
//
//	public List<TdModels> getModelByDescription(String search) {
//			return tr.findBy(search);
//	}
}
