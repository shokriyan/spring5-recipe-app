package guru.springframework.serives;

import java.util.Set;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes(); 
	Recipe findById(Long id);
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	RecipeCommand findRecipeCommandById(Long id); 
	void deleteById(Long l); 
}
