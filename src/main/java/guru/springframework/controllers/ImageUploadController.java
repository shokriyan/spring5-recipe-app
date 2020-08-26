package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import guru.springframework.serives.ImageService;
import guru.springframework.serives.RecipeService;

@Controller
public class ImageUploadController {

	private final ImageService imageService;
	private final RecipeService recipeService;
	
	
	public ImageUploadController(ImageService imageService, RecipeService recipeService) {
		this.imageService = imageService;
		this.recipeService = recipeService;
	} 
	
	
	@GetMapping("/recipe/{id}/image")
	public String newUploadForm(@PathVariable String recipeId, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(recipeId)));
		
		return "recipe/imageuploadform"; 
	}
	
	
	
}
