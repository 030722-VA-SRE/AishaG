//package com.revature.project_1;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.revature.controllers.TdController;
//import com.revature.models.TdModels;
//import com.revature.services.TdService;
//
//
//
//@WebMvcTest(TdController.class)
//public class TdControllerTest{
//	private List<TdModels> tdmodels;
//	private TdModels t;
//	@Autowired
//	private MockMvc mockmvc;
//	
//	@MockBean
//	private TdService ts;
//	
//	@Test
//	public void getModelById() throws Exception{
//		when(ts.getModelById(1)).thenReturn(tdmodels.get(0));
//		
//		this.mockmvc.perform(get("/{id}")).andDo(getModelById()).andExpect(status().isOk()).andExpect(content().json(tdmodels.get(1)));
//		
////		mockmvc.perform(MockMvcRequestBuilders.get("/id"))
////				.andExpect(status().isOk())
////				.andExpect(jsonPath("$").isMap())
////				.andExpect(jsonPath("id").value(1)
////				.andExpect(jsonPath("name").value("Mobius")
////				.andExpect(jsonPath("description").value("A snakey lady")
////				.andExpect(jsonPath("currentDate").value(04-21-1994)
////				.andExpect(jsonPath("price").value));
////				
////	
//	}
//}