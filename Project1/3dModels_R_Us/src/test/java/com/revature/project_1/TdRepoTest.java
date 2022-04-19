//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.revature.models.TdModels;
//import com.revature.repositories.TdModelRepository;
//
//@SpringBootTest
//class TdRepoTest {
//	
//	@Autowired
//	private TdModelRepository tr;
//	
//	@Test
//	void isTdModelByID() {
//		TdModels tdmodel = new TdModels(0, "Teri Teri", "Keeping things coolsince 1994.", null, 80.80, null);
//		(tr).save(tdmodel);
//		TdModels actualResult = tr.findById(0);
//		assertThat(actualResult).isTrue();
//	}
//}