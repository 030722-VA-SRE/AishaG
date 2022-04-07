//package com.revature.project_1;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.revature.controllers.TdController;
//import com.revature.exceptions.ItemNotFoundException;
//import com.revature.models.TdModels;
//import com.revature.repositories.TdModelRepository;
//import com.revature.services.TdService;
//
//@ExtendWith(MockitoExtension.class)
//
//
//public class ModelServiceTest {
//	
//	private List<TdModels> tdlist;
//	private int generatedId = 1;
//	@Mock 
//	private TdModelRepository tr;
//	
//	@InjectMocks
//	private TdService ts;
//	@InjectMocks
//	private TdModels td;
//	@InjectMocks
//	private TdController tdc;
//
//
//	@BeforeEach
//	public void setup() {
//	
//	TdModels tdmodels = TdModels.builder()
//		.id(1)
//		.modelName("Mobius")
//		.description("Big bank snake girl")
//		.currentDate(null)
//		.price(1000)
//		.creator(null)
//		.build();
//	
//	TdModels tdmodels1 = TdModels.builder()
//			.id(2)
//			.modelName("Klein")
//			.description("Tiny beta baby bulb")
//			.currentDate(null)
//			.price(50)
//			.creator(null)
//			.build();
//	
//	TdModels tdmodels2 = TdModels.builder()
//			.id(2)
//			.modelName("Orochimaru")
//			.description("Your girl from Konoha, the she of snakes")
//			.currentDate(null)
//			.price(666)
//			.creator(null)
//			.build();
//	
//	 tdlist = new ArrayList<>();
//	 tdlist.add(tdmodels);
//	 tdlist.add(tdmodels1);
//	 tdlist.add(tdmodels2);
//}
//	
//@DisplayName("Junit test for getAllModels method(success)")
//@Test
//public void getAll_returnTrue() {
//	
//	
//	
//	
//	
//	when(tr.findAll()).thenReturn(tdlist);
//	
//	assertEquals(tdlist, ts.getAllModels());
//	
//	assertThat(tdlist).isNotNull();
//	assertThat(tdlist.size()).isEqualTo(3);
//}
//
//@DisplayName("Junit test for getAllModels method(failure)")
//@Test
//public void getAll_returnFalse() {
//
//	
//	when(tr.findAll()).thenReturn(Collections.emptyList());
//	
//	tdlist = ts.getAllModels();
//	
//	assertThat(tdlist).isEmpty();
//	assertThat(tdlist.size()).isZero();
//}
//
//@DisplayName("Junit Test for getModelById(success)")
//@Test
//public void getModelByIdTest_returnTrue() throws ItemNotFoundException{
//	when(tr.findById(1)).thenReturn(Optional.of(tdlist.get(0)));
//	assertEquals(tdlist.get(0), ts.getModelById(1));
//	
//}
//
//@DisplayName("Junit Test for addModel method")
//@Test
//public void addModel_returnTrue() {
//	
//	when(tr.save(tdlist.get(1))).thenReturn(tdlist.get(1));
//		
//	TdModels newModel = ts.addModel(tdlist.get(1));
//	
//	assertThat(newModel).isNotNull();
//}
//
//@DisplayName("Junit Test for updateModelById method")
//@Test
//public void updateModelById_returnTrue() throws ItemNotFoundException {
//
//	TdModels updatedModel = TdModels.builder()
//			.id(2)
//			.modelName("KleinElf")
//			.description("Tiny beta realm controlling bulb")
//			.currentDate(null)
//			.price(100)
//			.creator(null)
//			.build();
//	
//	when(ts.getModelById(2)).thenReturn(tdlist.get(2));
//	
//	TdModels savedModel = tdlist.get(2);
//	when(tr.save(savedModel)).thenReturn(updatedModel);
//	
//	assertEquals(savedModel, updatedModel);
//
////	Optional<TdModels> updatedModel = tr.findById(1);
////	when(tr.save(tdlist.get(0))).thenReturn((updatedModel));
////	
////	updatedModel.setModelName("Orochimaru");
////	updatedModel.setDescription("Your girl from Konoha, the she of snakes");
////	
////		
////	TdModels modelUpdated = tdlist.get(2);
////	assertThat(modelUpdated.getModelName()).isEqualTo("Orochimaru");
////	assertThat(modelUpdated.getDescription()).isEqualTo("Your girl from Konoha, the she of snakes");
//
//	assertEquals(ts.getModelById(tdlist.get(0).getId()), updatedModel);
//}
//
////@DisplayName("JUnit test for deleteModelById method")
////@Test
////public void deleteModel_returntrue() {
////
////	.equals, td.deleteModelById(generatedId);
////	verify(td, times(1)).deleteModelById(generatedId);
////}
//
//}