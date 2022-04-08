package com.revature.project_1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.controllers.TdController;
import com.revature.dto.TDto;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.TdModels;
import com.revature.models.User;
import com.revature.repositories.TdModelRepository;
import com.revature.services.TdService;

@ExtendWith(MockitoExtension.class)


public class ModelServiceTest {
	
	private List<TdModels> tdlist;
	
	private int generatedId = 1;
	@Mock 
	private TdModelRepository tr;
	
	@Mock
	private TDto t;
	
	@InjectMocks
	private TdService ts;
	@InjectMocks
	private TdModels td;
	@InjectMocks
	private TdController tdc;


	@BeforeEach
	public void setup() {
	
	TdModels tdmodels = TdModels.builder()
		.modelId(1)
		.modelName("Mobius")
		.description("Big bank snake girl")
		.dateCreated(null)
		.price(1000)
		.creator(null)
		.build();
	
	TdModels tdmodels1 = TdModels.builder()
			.modelId(2)
			.modelName("Klein")
			.description("Tiny beta baby bulb")
			.dateCreated(null)
			.price(50)
			.creator(null)
			.build();
	
	TdModels tdmodels2 = TdModels.builder()
			.modelId(3)
			.modelName("Orochimaru")
			.description("Your girl from Konoha, the she of snakes")
			.dateCreated(null)
			.price(666)
			.creator(null)
			.build();

	 tdlist = new ArrayList<>();
	 tdlist.add(tdmodels);
	 tdlist.add(tdmodels1);
	 tdlist.add(tdmodels2);
	 
	 
}
	
@DisplayName("Junit test for getAllModels method(success)")
@Test
public void getAll_returnTrue() {
	
	
	when(ts.getAllModels()).thenReturn(tdlist);
	
	tdlist = tr.findAll();
	assertThat(tdlist).isNotNull();
	assertThat(tdlist.size()).isEqualTo(3);
}

@DisplayName("Junit test for getAllModels method(failure)")
@Test
public void getAll_returnFalse() {

	
	when(tr.findAll()).thenReturn(Collections.emptyList());
	
	tdlist = ts.getAllModels();
	
	assertThat(tdlist).isEmpty();
	assertThat(tdlist.size()).isZero();
}

@DisplayName("Junit Test for getModelById(success)")
@Test
public void getModelByIdTest_returnTrue(){
	when(tr.findById(anyInt())).thenReturn(Optional.of(td));
	
	TDto t = ts.getModelById(1);
	
	
}

@DisplayName("Junit Test for addModel method")
@Test
public void addModel_returnTrue() {
	
	when(tr.save(tdlist.get(1))).thenReturn(tdlist.get(1));
		
	TdModels newModel = ts.addModel(tdlist.get(1));
	
	assertThat(newModel).isNotNull();
}

@DisplayName("Junit Test for updateModelById method")
@Test
public void updateModelById_returnTrue() {

	TdModels tdmodel2 = new TdModels(2, "tester", "istest", null, 2, null);
	TdModels tdmodel3 = new TdModels(3, "tester3", "istest3", null, 3, null);
	User user2 = new User();
	when(tr.findById(anyInt())).thenReturn(Optional.of(tdmodel3));
//	when(tdmodel2.setModelName(ArgumentMatchers.anyString() && !tdmodel2.getModelName().equals(tdmodel3)).thenReturn(tdmodel3.setDescription(tdmodel2.getDescription())));
	
//	TdModels savedModel = tdlist.get(2);
	
	when(tr.save(any(TdModels.class))).thenReturn(tdmodel2);
	assertThat(new TDto(tdmodel2)).isEqualTo(tdmodel2);

//	Optional<TdModels> updatedModel = tr.findById(1);
//	when(tr.save(tdlist.get(0))).thenReturn((updatedModel));
//	
//	updatedModel.setModelName("Orochimaru");
//	updatedModel.setDescription("Your girl from Konoha, the she of snakes");
//	
//		
//	TdModels modelUpdated = tdlist.get(2);
//	assertThat(modelUpdated.getModelName()).isEqualTo("Orochimaru");
//	assertThat(modelUpdated.getDescription()).isEqualTo("Your girl from Konoha, the she of snakes");

//	assertEquals(ts.getModelById(tdlist.get(0).getId()), updatedModel);
}

@DisplayName("JUnit test for deleteModelById method")
@Test
public void deleteModel_returntrue() {

	when(tr.findById(anyInt())).thenReturn(Optional.of(td));
	doNothing().when(tr).delete(any(TdModels.class));
	ts.deleteModelById(generatedId);
	verify(tr, times(1)).delete(td);
}

}