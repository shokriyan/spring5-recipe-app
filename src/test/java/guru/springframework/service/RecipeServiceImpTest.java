package guru.springframework.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.serives.RecipeService;
import guru.springframework.serives.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecipeServiceImpTest {
	
	RecipeService recipeService; 
	
	@Mock
	RecipeRepository recipeRepository; 
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository);
		
	}
	
	@Test
	public void getRecipes() throws Exception {
		Recipe recipe = new Recipe(); 
		HashSet<Recipe> recipeData = new HashSet<Recipe>(); 
		recipeData.add(recipe);
		when(recipeRepository.findAll()).thenReturn(recipeData);
		Set<Recipe> recipes = recipeService.getRecipes(); 
		assertEquals(recipes.size(), 1);
		log.debug("Recipes List :::: " + recipeData);
		verify(recipeRepository, times(1)).findAll();
	}

}
