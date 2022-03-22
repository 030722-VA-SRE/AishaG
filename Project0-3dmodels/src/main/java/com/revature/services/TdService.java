package com.revature.services;

import java.util.List;

import com.revature.daos.TdDao;
import com.revature.daos.TdPostgres;
import com.revature.models.TdModels;

public class TdService{

	private TdDao tDao; //= new TdPostgres();
	
	public TdService(TdDao mockDao) {
		tDao = mockDao;
	}
	public TdService() {
		tDao = new TdPostgres();
	}

	public TdModels getModelById(int id) {
		return tDao.getModelById(id);
	}

	public List<TdModels> getAllModels() {
		return tDao.getAllModels();
	}
	
	public boolean addTdModels(TdModels newTdModels) {
		int generatedId = tDao.addTdModels(newTdModels);
		if(generatedId != -1) {
			return true;
		}
		return false;
	}
	
	public boolean updateTdModel(TdModels tdmodels) {
		boolean rowsChanged = tDao.updateTdModel(tdmodels);
		return rowsChanged;
	}
	
	public boolean deleteTdModelById(int id) {
		boolean deletedModel = tDao.deleteTdModelById(id);
		return deletedModel;
	}
	
	public List<TdModels> getModelByPrice(double price) {
		return tDao.getModelByPrice(price);
	}
	
	public List<TdModels> getModelByDescription(String search) {
			return tDao.getModelByDescription(search);
	}
}

////public List<TdModels> getModelsByUploadDate(Date date){
////return tDao.getModelsByUploadDate(date);
//}