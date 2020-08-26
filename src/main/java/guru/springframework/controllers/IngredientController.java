package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.serives.IngredientService;
import guru.springframework.serives.RecipeService;
import guru.springframework.serives.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {
	
	private final RecipeService recipeService;
	private final IngredientService ingredientService; 
	private final UnitOfMeasureService unitOfMeasureService; 
	
	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}



	@GetMapping("/recipe/{recipeId}/ingredients")
	public String ingredientList(@PathVariable String recipeId, Model model) {
		log.debug("get ingredeint for recipe Id " + recipeId);
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(recipeId)));
		
		return "recipe/ingredient/list";
	}
	
	
	
	@GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeId,@PathVariable String id, Model model){
		
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		
		return "recipe/ingredient/show"; 
	}
	
	@GetMapping("recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id))); 
		model.addAttribute("uomList", unitOfMeasureService.listAlUoms()); 
		return "recipe/ingredient/ingredientForm";
	}
	
	@GetMapping("/recipe/{recipeId}/ingredient/new")
	public String newIngredient(@PathVariable String recipeId, Model model) {
		RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
		IngredientCommand ingredientCommand = new IngredientCommand(); 
		ingredientCommand.setRecipeId(Long.valueOf(recipeId));
		model.addAttribute("ingredient" , ingredientCommand); 
		ingredientCommand.setUom(new UnitOfMeasureCommand());
		model.addAttribute("uomList", unitOfMeasureService.listAlUoms());
		return "recipe/ingredient/ingredientForm";
	}
	
	@PostMapping("recipe/{recipeId}/ingredient")
	public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand command) {
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
		log.debug("Saved Recipe Id: " + savedCommand.getRecipeId());
		log.debug("Saved Ingredient id: " + savedCommand.getId()); 
		
		return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredients";
	}
	
	@GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
	public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
		RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
		
		ingredientService.deleteByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		
		
		
		return "redirect:/recipe/"+recipeCommand.getId()+"/ingredients"; 
	}

}
