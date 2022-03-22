package com.revature.daos;

import java.util.List;

import com.revature.models.TdModels;

public interface TdDao {
	
	public List<TdModels> getAllModels();
	public TdModels getModelById(int id);
	public int addTdModels(TdModels newTdModels);
	public boolean updateTdModel(TdModels tdmodels);
	public boolean deleteTdModelById(int id);
	public List<TdModels> getModelByPrice(double price);
	public List<TdModels> getModelByDescription(String search);
	
}

//public List<TdModels> getModelsByUploadDate(Date date);
//public List<TdModels> getModelsByUserId(int userId);