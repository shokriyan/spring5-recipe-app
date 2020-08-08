package guru.springframework.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repository.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository; 
	
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	
	@Test
	public void findByDescriptionTest() throws Exception {
		
		Optional<UnitOfMeasure> optional = unitOfMeasureRepository.findByDescription("Tablespoon");
		assertEquals("Tablespoon", optional.get().getDescription());
	}
	
	@Test
	public void findByDescriptionCupTest() throws Exception {
		
		Optional<UnitOfMeasure> optional = unitOfMeasureRepository.findByDescription("Cup");
		assertEquals("Cup", optional.get().getDescription());
	}

}
