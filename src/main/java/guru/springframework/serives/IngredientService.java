package guru.springframework.serives;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {
	
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	IngredientCommand saveIngredientCommand(IngredientCommand command);
	void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
