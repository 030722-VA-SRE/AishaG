package com.revature;

//import java.util.ArrayList;
//import java.util.List;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.http.HttpStatus;

//import com.revature.daos.TdDao;
//import com.revature.daos.TdPostgres;
//import com.revature.exceptions.ItemNotFoundException;
import com.revature.services.TdService;
import com.revature.models.TdModels;
//import com.revature.models.User;
//import com.revature.persistence.TdArrayList;
//import com.revature.services.UserService;

import io.javalin.Javalin;

public class Driver {
	private static Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		TdService ts = new TdService();
		
		Javalin app = Javalin.create();
		app.start(8081);
		log.info("Javalin started~");
		
		/*-
		 * Basic endpoint set up returning plain txt
		 */
		app.get("/", (ctx) -> ctx.result("Welcome to 3d Heaven!"));
	
		app.get("tdmodels", (ctx) -> {
			
			
			String price = ctx.queryParam("price");
			String searchTerm = ctx.queryParam("has");
			
			if(price == null && searchTerm == null) {
				ctx.json(ts.getAllModels());
			}
			else if(price != null && searchTerm == null) {	
				List<TdModels> tdmodels = ts.getModelByPrice(Double.parseDouble(price));
				ctx.json(tdmodels);
			}
			else if(searchTerm != null && price == null) {
				List<TdModels> tdmodels = ts.getModelByDescription(searchTerm);
				ctx.json(tdmodels);
			}
//			
//		
//			if(price == 0) {
//				ctx.status(404);
//				ctx.result("Item of price: " + price + " was not found");
//			} else {
//				ctx.json(td);
//				ctx.status(200);
//			}
//			
		});
		
	app.get("tdmodels/{id}", (ctx) -> {
		
		/*-
		 Tdmodels 
		 *  here the id value requested is passed via a path param where {id} represents the value, ie:
		 *  	- localhost:8080/tasks/2
		 *  	- localhost:8080/tasks/5
		 *  we are retrieving this value as a string using the ctx object which we are then converting to an int for manipulation
		 */
		int id = Integer.parseInt(ctx.pathParam("id"));
		TdModels td = ts.getModelById(id);
		
		if(td.getModelName() == null) {
			ctx.status(404);
			ctx.result("Item of id: " + id + " was not found");
		} else {
			ctx.json(td);
			ctx.status(200);
		}
		
	});

	
	app.post("newmodel", (ctx) ->{
		TdModels newTdModels = ctx.bodyAsClass(TdModels.class);
		
		/*-
		 * if the task already exists, returns status code 400 with a message about why
		 * else if the task doesn't exist, add the task to the arraylist and set the status code to 201 
		 */
		if(ts.addTdModels(newTdModels)) {
			ctx.status(HttpStatus.CREATED_201);
			log.info("new model added!");	
		}else {
			ctx.status(400);
			ctx.result("Model of name '" + newTdModels.getModelName() + "' could not be made.");
		}
	});

	app.delete("remove_model/{id}", (ctx) ->{
		// retrieving id from the path param and converting to an int
		String pathParamId = ctx.pathParam("id");
		int id = Integer.parseInt(pathParamId);
		
		/*-
		 *  set a default response to be overriden if a task is deleted
		 *  	- ie: if no tasks of that id is found in the arrayList no task is deleted
		 */
		boolean deletedTask = ts.deleteTdModelById(id);
		if(deletedTask) {
			ctx.status(200);
			ctx.result("Model deleted.");
			log.info("User removed a model-.-");
		} else {
			ctx.status(404);
			ctx.result("Model not found");
		}
	});

	app.put("update_model/{id}", (ctx) ->{

		TdModels tdmodels = ctx.bodyAsClass(TdModels.class);
		// retrieving id from the path param and converting to an int
		
		/*-
		 *  set a default response to be overriden if a task is deleted
		 *  	- ie: if no tasks of that id is found in the arrayList no task is deleted
		 */
		boolean updateModel = ts.updateTdModel(tdmodels);
		if(updateModel) {
			ctx.status(200);
			ctx.result("Model updated.");
			log.info("A model has been changed ?.?");
		} else {
			ctx.status(404);
			ctx.result("Model not found");
		}
	});	
	}
}
		//UserService us = new UserService();

//System.out.println("getById:" + us.getById(2));
//		
//		User u = new User();
//		u.setUsername("Test");
//		u.setPassword("testpass");
//		
//		us.addUser(u);
//		
//		List<User> users = us.getAll();
//		for(User user : users) {
//			System.out.println(user);
//		}
//	}
	
//	public static void basicJdbcSetup() {
//		/*-
//		 * to setup a connection
//		 * 	- url
//		 * 	- username
//		 * 	- password
//		 */
//		// set up url jdbc:postgresql://[db-url]:5432/[db-name]";
//		String url = "database-1.chskbqpdd4ru.us-east-1.rds.amazonaws.com:5432/postgres";
//		String username = "Byakuya";
//		String password = "senbonzakura";
		
		// because Connection has/can be closed, try with resources closes the resource for us
//		try(Connection c = DriverManager.getConnection(url, username, password)) {
//			System.out.println(c.getMetaData().getDriverName());
//			
//			//c.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//		public static void basicJavalinSetup() {
//		
//		
//		 //* Basic endpoint returning a JSON object
//
//		app.get("json", (ctx) ->{
//			TdModels t = new TdModels();
//			t.setName("Testing Javalin!");
//			t.getCurrentDate();
//			ctx.json(t);
//		});
//		
//		/*-
//		 * Basic endpoint deserializing JSON back to a Java task object
//		 */
//		app.post("json", (ctx) ->{
//			TdModels t = ctx.bodyAsClass(TdModels.class);
//			System.out.println(t);
//		});
//	}
////		/*-
////		 * All in one application setup, here we are using an ArrayList to mimic the idea of persistence.
////		 * 	- Tasks added via the API will be added to the ArrayList
////		 * 	- Tasks can then be retrieved by iterating through the ArrayList to retrieve/modify the desired tasks 
////		 */	
//		public static void basicTaskApp() {
//			
//		
//			
//			Javalin app = Javalin.create().start(8081);
//		
//			
//
//		
//}
//		app.post("models", (ctx) ->{
//			TdModels newmodel = ctx.bodyAsClass(TdModels.class);
//			
//			/*-
//			 * example of some validation:
//			 * 	- if the task name already exists, does not add a new task to the ArrayList
//			 */
//			int generatedId = tdDao.addTdModels(newmodel);
//			/*-
//			 * if the task already exists, returns status code 400 with a message about why
//			 * else if the task doesn't exist, add the task to the arraylist and set the status code to 201 
//			 */
//			if(generatedId == -1) {
//				ctx.status(400);
//				ctx.result("Model of name: '" + newmodel.getName() + "' already exists.");
//			}else {
//				ctx.status(HttpStatus.CREATED_201);
//			}
//		});
//		
//		app.delete("model/{id}", (ctx) ->{
//			// retrieving id from the path param and converting to an int
//			String pathParamId = ctx.pathParam("id");
//			int modelId = Integer.parseInt(pathParamId);
//			
//			/*-
//			 *  set a default response to be overriden if a task is deleted
//			 *  	- ie: if no tasks of that id is found in the arrayList no task is deleted
//			 */
//			
//			boolean deletedModel = tdDao.deleteTdModelById(modelId);
//			if(deletedModel) {
//				ctx.status(200);
//			} else {
//				ctx.status(404);
//			}
//		});
//	}

//int id = Integer.parseInt(ctx.pathParam("id"));

