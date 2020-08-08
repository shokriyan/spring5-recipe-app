package guru.springframework.serives;

import java.util.Set;

import guru.springframework.domain.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes(); 
}
