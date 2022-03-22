package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.eclipse.jetty.util.thread.Scheduler.Task;

import com.revature.models.TdModels;
import com.revature.util.ConnectionUtil;

public class TdPostgres implements TdDao {
	private static Logger log = LogManager.getRootLogger();
	//Get all models - should I leave code for returning userId of creator? 
	@Override
	public List<TdModels> getAllModels() {
		String sql = "select * from tdmodels order by id;";
		List<TdModels> tdmodels = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
		while (rs.next()) {
			TdModels newModel = new TdModels();
			newModel.setId(rs.getInt("id"));
			newModel.setModelName(rs.getString("model_name"));
			newModel.setDescription(rs.getString("description"));
			newModel.setCurrentDate(rs.getDate("upload_date"));
			newModel.setPrice(rs.getDouble("price"));

			tdmodels.add(newModel);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		log.error("Exception detected with getAllModels()");
	}
		return tdmodels;
		}

//Get Model by Model Id
	@Override
	public TdModels getModelById(int id) {
		String sql = "select * from tdmodels where id = ?;";
		TdModels newModel = new TdModels();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				newModel.setId(rs.getInt("id"));
				newModel.setModelName(rs.getString("model_name"));
				newModel.setDescription(rs.getString("description"));
				newModel.setCurrentDate(rs.getDate("upload_date"));
				newModel.setPrice(rs.getDouble("price"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Exception detected with getModelById()");
		}
		return newModel;
	}
//Add new models
	@Override
		public int addTdModels(TdModels newTdModels){
		String sql = "insert into tdmodels(model_name, description, price) values (?,?,?) returning id;";
		int generatedId = -1;

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, newTdModels.getModelName());
			ps.setString(2, newTdModels.getDescription());
			ps.setDouble(3, newTdModels.getPrice());
			
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				generatedId = rs.getInt("id");
			}
		} catch (SQLException e) {
			log.error("Exception detected with newModel()");
			e.printStackTrace();
		}
		return generatedId;
	}
//Update current models - Would like to make a feature to prevent non-creator/contributor access
	@Override
	public boolean updateTdModel(TdModels tdmodels) {
		String sql = "update tdmodels set model_name = ?, description = ?, price = ? where id = ?;";
		int rowsChanged = -1;

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);
		
			ps.setString(1, tdmodels.getModelName());
			ps.setString(2, tdmodels.getDescription());
			ps.setDouble(3, tdmodels.getPrice());
			ps.setInt(4, tdmodels.getId());

			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Exception detected with updateTdModels()");
		}
		if (rowsChanged < 1) {
			return false;
		}

		System.out.println("Record updated successfully");
		return true;
		
	}

	//Delete model- would like to be able to keep non-creator from being able to use this
	@Override
	public boolean deleteTdModelById(int id) {
		String sql = "delete from tdmodels where id = ?;";
		int rowsChanged = -1;

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);

			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Exception detected with deleteTdModels()");
		}
		if (rowsChanged < 1) {
			return false;
		}

		return true;
	}
	
	public List<TdModels> getModelByPrice(double price) {
		
		String sql = "select * from tdmodels where price = ? order by id;";
		
		List<TdModels> tdmodels = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);
			

			ps.setDouble(1, price);
			
			ResultSet rs = ps.executeQuery();
			

			while (rs.next()) {
				TdModels newModel = new TdModels();
				newModel.setId(rs.getInt("id"));
				newModel.setModelName(rs.getString("model_name"));
				newModel.setCurrentDate(rs.getDate("upload_date"));
				newModel.setDescription(rs.getString("description"));
				newModel.setPrice(rs.getDouble("price"));
				tdmodels.add(newModel);
			}
		}
		
	catch (SQLException e) {
		e.printStackTrace();
		log.error("Exception detected with getModelByPrice()");
		}
	return tdmodels;
	}
	
public List<TdModels> getModelByDescription(String search) {
		
		String sql = "select * from tdmodels where description like ? order by id;";
		
		List<TdModels> tdmodels = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, "%" +search+ "%");
			
			ResultSet rs = ps.executeQuery();
			

			while (rs.next()) {
				TdModels newModel = new TdModels();
				newModel.setId(rs.getInt("id"));
				newModel.setModelName(rs.getString("model_name"));
				newModel.setCurrentDate(rs.getDate("upload_date"));
				newModel.setDescription(rs.getString("description"));
				newModel.setPrice(rs.getDouble("price"));
				tdmodels.add(newModel);
			}
		}
		
	catch (SQLException e) {
		e.printStackTrace();
		log.error("Exception detected with getModelByDescription()");
		}
	return tdmodels;
	}
}

////Get models by Upload Date
//@Override
//public List<TdModels> getModelsByUploadDate(Date date) {
//	String sql = "select * from tdmodels where upload_date = ?;";
//	List<TdModels> tdmodels = new ArrayList<>();
//
//	try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
//		PreparedStatement ps = c.prepareStatement(sql);
//
//		ps.setDate(1, date);
//
//		ResultSet rs = ps.executeQuery();
//
//		while (rs.next()) {
//			TdModels newModel = new TdModels();
//			newModel.setId(rs.getInt("id"));
//			newModel.setModelName(rs.getString("model_name"));
//			newModel.setDescription(rs.getString("description"));
//			newModel.setCurrentDate(rs.getDate("upload_date"));
//			newModel.setPrice(rs.getDouble("price"));
//			
////			User userAssigned = new User();
////			userAssigned.setId(rs.getInt("user_assigned_id"));
////			newModel.setUserAssigned(userAssigned);
//
//			tdmodels.add(newModel);
//		}
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return tdmodels;
//}
//@Override
//public List<TdModels> getModelsByUserId(int userId) {
//	String sql = "select * from tdmodels where user_assigned_id = ?;";
//	List<TdModels> tdmodels = new ArrayList<>();
//
//	try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
//		PreparedStatement ps = c.prepareStatement(sql);
//
//		ps.setInt(1, userId);
//
//		ResultSet rs = ps.executeQuery();
//
//		while (rs.next()) {
//			TdModels newModel = new TdModels();
//			newModel.setId(rs.getInt("id"));
//			newModel.setModelName(rs.getString("model_name"));
//			newModel.setDescription(rs.getString("description"));
//			newModel.setCurrentDate(rs.getDate("upload_date"));
//			newModel.setPrice(rs.getDouble("price"));
//			
//			User userAssigned = new User();
//			userAssigned.setId(rs.getInt("user_assigned_id"));
//			newModel.setUserAssigned(userAssigned);
//
//			tdmodels.add(newModel);
//		}
//	} catch (SQLException e) {
//		e.printStackTrace();
//		log.error("Exception detected with getModelsById()");
//	}
//	return tdmodels;
//}