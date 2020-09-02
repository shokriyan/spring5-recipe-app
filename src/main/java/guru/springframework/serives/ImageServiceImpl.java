package guru.springframework.serives;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository; 
	
	

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}



	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		try {
			Recipe recipe = recipeRepository.findById(recipeId).get(); 
			log.info("Finde Recipe :::: " + recipe.getId());
			Byte[] byteObject = new Byte[file.getBytes().length]; 
			int i = 0; 
			for (byte b: file.getBytes()) {
				byteObject[i++] = b; 
			}
			
			recipe.setImage(byteObject);
			
			recipeRepository.save(recipe); 
			
		}catch (IOException e) {
			log.error("Exception accured");
			e.printStackTrace(); 
		}
	}

}
