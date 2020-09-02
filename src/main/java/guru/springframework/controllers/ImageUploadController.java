package guru.springframework.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.commands.RecipeCommand;
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
	public String newUploadForm(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
		
		return "recipe/imageuploadform"; 
	}
	
	@PostMapping("recipe/{id}/image")
	public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
		imageService.saveImageFile(Long.valueOf(id), file);
		return "redirect:/recipe/" + id + "/show";
	}
	
	@GetMapping("recipe/{id}/recipeimage")
	public void renderImageFromDB(@PathVariable String id, HttpServletResponse response ) throws IOException {
		RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(id)); 
		byte[] byteArray = new byte[recipeCommand.getImage().length];
		
		int i = 0; 
		
		for (Byte wrappedByte: recipeCommand.getImage()) {
			byteArray[i++] = wrappedByte; 
		}
		response.setContentType("image/jpeg");
		InputStream inputStream = new ByteArrayInputStream(byteArray); 
		IOUtils.copy(inputStream, response.getOutputStream()); 
		
	}
	
	
	
}
