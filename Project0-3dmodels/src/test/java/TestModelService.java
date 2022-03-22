import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.daos.TdDao;
import com.revature.models.TdModels;
import com.revature.services.TdService;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)

public class TestModelService{
	
	private TdDao mockDao;
	private TdService td;
	private List<TdModels> tdmodel;
	private int generatedId = 1;
	private TdModels t;
	private TdModels incompleteModels = null;
	
	@Before
	public void setup() {
	mockDao = mock(TdDao.class);
	td = new TdService(mockDao);
	

	tdmodel = new ArrayList<TdModels>();
	tdmodel.add(new TdModels(1, "Model1", "Is model", 0));
	tdmodel.add(new TdModels(2, "Model2", "Is model 2", 0));
	tdmodel.add(new TdModels(3, "Model3", "Is model 3", 0));
	
	
	}
	
	@Test
	public void getModelByIdTest() {
		when(mockDao.getModelById(1)).thenReturn(tdmodel.get(0));
		assertEquals(tdmodel.get(0), td.getModelById(1));
		verify(mockDao, times(1)).getModelById(1);
		}
	
	@Test
	public void getAllModelsTest() {
		when(mockDao.getAllModels()).thenReturn(tdmodel);
		assertEquals(tdmodel, td.getAllModels());
		verify(mockDao, times(1)).getAllModels();
		}
	
	@Test
	public void addTdModelsTest0() {
		boolean itemChanged = true;
		t = tdmodel.get(2);
		when(mockDao.addTdModels(t)).thenReturn(generatedId);
		assertEquals(td.addTdModels(t), itemChanged);
		verify(mockDao, times(1)).addTdModels(t);
	}
	
	@Test
	public void addTdModelsTest1() {
		int generatedId = -1;
		when(mockDao.addTdModels(incompleteModels)).thenReturn(generatedId);
		assertFalse(td.addTdModels(null));
		//verify(mockDao, times(1)).addTdModels(tdmodel.get(generatedId));
	}
	
@Test
	public void updateTdModelTest() {
	boolean itemChanged = true;
	
	t = tdmodel.get(2);
		when(mockDao.updateTdModel(tdmodel.get(2))).thenReturn(itemChanged);
		assertEquals(td.updateTdModel(tdmodel.get(2)), itemChanged);
	}
	
@Test
	public void deleteTdModelById() {
		boolean itemChanged = true;
		t = tdmodel.get(2);
		when(mockDao.deleteTdModelById(generatedId)).thenReturn(itemChanged);
		assertTrue(td.deleteTdModelById(generatedId));
		verify(mockDao, times(1)).deleteTdModelById(generatedId);
	}
	
	
@Test
	public void getModelByPriceTest() {
	when(mockDao.getModelByPrice(0)).thenReturn(tdmodel);
	assertEquals(tdmodel, td.getModelByPrice(0));
	verify(mockDao, times(1)).getModelByPrice(0);
	}
	
@Test
	public void getModelByDescriptionTest() {
	when(mockDao.getModelByDescription("")).thenReturn(tdmodel);
	assertEquals(tdmodel, td.getModelByDescription(""));
	verify(mockDao, times(1)).getModelByDescription("");
	}
}