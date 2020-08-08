package guru.springframework.serives;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{
	
	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("Im in Recipe Service");
		
		Set<Recipe> recipes = new HashSet<Recipe>(); 
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		
		return recipes;
	} 
	
	
	

}
