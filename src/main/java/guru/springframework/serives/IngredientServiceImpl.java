package guru.springframework.serives;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		if (!recipeOptional.isPresent()) {
			log.error("REcipe Id not find recipe list" + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredient().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			log.error("Ingredient Not Find with Ingredient Id" + ingredientId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		if (!recipeOptional.isPresent()) {
			log.error("Recipe not found for Id ::: " + command.getRecipeId());

			return new IngredientCommand();
		} else {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredient().stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("UOM Not found")));
			} else {
				recipe.addIngredient(ingredientCommandToIngredient.convert(command));
			}

			Recipe savedRecipe = recipeRepository.save(recipe);
			
			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredient().stream()
			.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
			.findFirst(); 
			
			if (!savedIngredientOptional.isPresent()) {
				savedIngredientOptional = savedRecipe.getIngredient().stream()
						.filter(recipeIngredient -> recipeIngredient.getDescription().equals(command.getDescription()))
						.filter(recipeIngredient -> recipeIngredient.getAmount().equals(command.getAmount()))
						.filter(recipeIngredient -> recipeIngredient.getUom().getId().equals(command.getUom().getId()))
						.findFirst(); 
			}

			IngredientCommand savedIngredientCommand = ingredientToIngredientCommand.convert(savedIngredientOptional.get());

			return savedIngredientCommand;
		}

	}

	@Override
	public void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> RecipeOptional = recipeRepository.findById(recipeId); 
		if (RecipeOptional.isPresent()) {
			log.debug("Recipe Found");
			Recipe recipe = RecipeOptional.get();
			
			Optional<Ingredient> ingredientOptional = recipe.getIngredient()
					.stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
					.findFirst(); 
			
			if (ingredientOptional.isPresent()) {
				log.debug("Ingredient Found");
				Ingredient ingredientToDelete = ingredientOptional.get(); 
				ingredientToDelete.setRecipe(null);
				recipe.getIngredient().remove(ingredientToDelete); 
				recipeRepository.save(recipe); 
			}
		}else {
			log.error("Recipe Not Found ");
		}

		
	}


	
}
