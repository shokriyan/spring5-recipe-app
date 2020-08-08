package guru.springframework.domain;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Category;

public class CategoryTest {
	
	Category category; 
	
	@Before
	public void setUp() {
		category = new Category(); 
		category.setId(1L);
		category.setDescription("Test Category");
	}
	
	@Test
	public void getIdTest() {
		Long expectedValue = 1L ; 
		assertEquals(expectedValue, category.getId());
		
	}
	@Test
	public void getDescriptionTest() {
		String expectedValue = "Test Category"; 
		assertEquals(expectedValue, category.getDescription());
	}

}
