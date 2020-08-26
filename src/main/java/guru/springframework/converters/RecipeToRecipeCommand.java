package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	private final CategoryToCategoryCommand categoryConverter; 
	private final NotesToNotesCommand notesConverter; 
	private final IngredientToIngredientCommand ingredientConverter; 
	
	
	
	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter,
			IngredientToIngredientCommand ingredientConverter) {
		this.categoryConverter = categoryConverter;
		this.notesConverter = notesConverter;
		this.ingredientConverter = ingredientConverter;
	}



	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null)
			return null; 
		
		RecipeCommand recipeCommand = new RecipeCommand(); 
		recipeCommand.setId(source.getId());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setServings(source.getServings());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setUrl(source.getUrl());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
		
		if (source.getIngredient() != null && source.getIngredient().size() > 0) {
			source.getIngredient()
			.forEach(ingredient -> recipeCommand.getIngredient().add(ingredientConverter.convert(ingredient)));
		}
		
		if (source.getCategories() != null & source.getCategories().size() > 0) {
			source.getCategories().forEach(category -> {
				recipeCommand.getCategories().add(categoryConverter.convert(category));
			});
			
		}
		
		return recipeCommand;
	}

}
