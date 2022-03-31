//package com.revature.project_1;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.revature.controllers.TdController;
//import com.revature.models.TdModels;
//import com.revature.services.TdService;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = TdController.class)
//public class TdControllerTest{
//	@Autowired
//	MockMvc mockmvc;
//	
//	@MockBean
//	TdService ts;	
//	
//	@Test
//	public void getModelById() throws Exception{
//		given(ts.getModelById(Mockito.anyString())).willReturn(new TdModels(1,"Mobius","A snakey lady"));
//		
//		mockmvc.perform(MockMvcRequestBuilders.get("/id"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$").isMap())
//				.andExpect(jsonPath("id").value(1)
//				.andExpect(jsonPath("name").value("Mobius")
//				.andExpect(jsonPath("description").value("A snakey lady")
//				.andExpect(jsonPath("currentDate").value(04-21-1994)
//				.andExpect(jsonPath("price").value)		);
//				
//	
//	}
//}