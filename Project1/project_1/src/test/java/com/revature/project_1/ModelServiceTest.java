//package com.revature.project_1;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.revature.models.TdModels;
//import com.revature.repositories.TdModelRepository;
//import com.revature.services.TdService;
//
//@ExtendWith(MockitoExtension.class)
//
//
//class ModelServiceTest {
//	
//	@Mock private TdModelRepository tr;
//	
//	private TdService td;
//	
//	@BeforeEach void setup() {
//		this.td = new TdService(this.tr);
//	}
//	
//}