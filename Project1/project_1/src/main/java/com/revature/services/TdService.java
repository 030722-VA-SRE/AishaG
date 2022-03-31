package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.TdModels;
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

	public TdModels getModelById(int id) throws ItemNotFoundException {
		return tr.findById(id).orElseThrow(ItemNotFoundException::new);
	}
	@Transactional
	public TdModels addModel(TdModels newTdModel) {
		return tr.save(newTdModel);
	}
	
	public List<TdModels> getAllModels() {
		return tr.findAll();
	}
	
	@Transactional
	public TdModels updateModelById(int id, TdModels tdmodel) {
		return tr.save(tdmodel);
	}
	
	public void deleteModelById(int id) throws ItemNotFoundException{
		getModelById(id);
		tr.deleteById(id);
	}
}
//	public List<TdModels> getModelByPrice(double price) {
//		return tr.findBy(price);
//	}
//	
//	public List<TdModels> getModelByDescription(String search) {
//			return tDao.getModelByDescription(search);
//	}
//}
