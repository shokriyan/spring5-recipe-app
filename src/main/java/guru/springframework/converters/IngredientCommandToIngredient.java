package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand	, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure uomConvertor;
	
	
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConvertor) {
		this.uomConvertor = uomConvertor;
	}


	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null)
			return null; 
		
		Ingredient ingredient = new Ingredient(); 
		ingredient.setId(source.getId());
		
		if (source.getRecipeId() != null) {
			Recipe recipe = new Recipe(); 
			recipe.setId(source.getRecipeId());
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient); 
		}
		
		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		ingredient.setUom(uomConvertor.convert(source.getUom()));
		return ingredient;
	}

}
