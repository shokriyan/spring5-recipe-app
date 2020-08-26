package guru.springframework.bootstrapdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.debug("Loding boo");
		recipeRepository.saveAll(getRecipes());
		
	}



	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();

		Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		if (!teaspoonOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Teaspoon Not Found");
		}

		Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		if (!tablespoonOptional.isPresent()) {
			throw new RuntimeException("Expected UOM tablespoon Not Found");
		}

		Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");
		if (!cupOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Cup Not Found");
		}

		Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
		if (!pinchOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Pinch Not Found");
		}

		Optional<UnitOfMeasure> ounchOptional = unitOfMeasureRepository.findByDescription("Ounce");
		if (!ounchOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Ounce Not Found");
		}
		
		Optional<UnitOfMeasure> ripeOptional = unitOfMeasureRepository.findByDescription("Ripe");
		if (!ripeOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Ripe Not Found");
		}

		Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription("Pint");
		if (!pintOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Pint Not Found");
		}

		Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByDescription("Dash");
		if (!dashOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Dash Not Found");
		}

		Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByDescription("Each");
		if (!eachOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Each Not Found");
		}

		UnitOfMeasure teaspoonUOM = teaspoonOptional.get();
		UnitOfMeasure tablespoonUOM = tablespoonOptional.get();
		UnitOfMeasure cupUOM = cupOptional.get();
		UnitOfMeasure pinchUOM = pinchOptional.get();
		UnitOfMeasure ounceUOM = ounchOptional.get();
		UnitOfMeasure ripeUOM = ripeOptional.get(); 
		UnitOfMeasure pintUOM = pintOptional.get();
		UnitOfMeasure dashUOM = dashOptional.get();
		UnitOfMeasure eachUOM = eachOptional.get();

		Optional<Category> americanOptional = categoryRepository.findByDescription("American");
		if (!americanOptional.isPresent()) {
			throw new RuntimeException("Expected Category American Not Found");
		}
		Optional<Category> italianOptional = categoryRepository.findByDescription("Italian");
		if (!italianOptional.isPresent()) {
			throw new RuntimeException("Expected Category Italian Not Found");
		}
		Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");
		if (!mexicanOptional.isPresent()) {
			throw new RuntimeException("Expected Category Mexican Not Found");
		}
		Optional<Category> fastFoodOptional = categoryRepository.findByDescription("Fast Food");
		if (!fastFoodOptional.isPresent()) {
			throw new RuntimeException("Expected Category Fast Food Not Found");
		}

		Category americanCategory = americanOptional.get();
		Category itanialCategory = italianOptional.get();
		Category mexicanCategory = mexicanOptional.get();
		Category fastFoodCategory = fastFoodOptional.get();

		// Guacamoli Recipe
		Recipe guaco = new Recipe();
		guaco.setDescription("GUACAMOLE VARIATIONS");
		guaco.setPrepTime(10);
		guaco.setCookTime(10);
		guaco.setDifficulty(Difficulty.EASY);
		guaco.setDirections(
				"1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"
						+ "\n" + "\n" + "\n"
						+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n"
						+ "\n" + "\n" + "\n"
						+ "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n"
						+ "\n"
						+ "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n"
						+ "\n"
						+ "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n"
						+ "\n"
						+ "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n"
						+ "\n"
						+ "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
		Notes guacoNote = new Notes(); 
		guacoNote.setRecipeNote("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
		guaco.setNotes(guacoNote);
		
		guaco.addIngredient(new Ingredient("avocados", new BigDecimal(2), ripeUOM));
		guaco.addIngredient(new Ingredient("salt, more to taste", new BigDecimal(1.4), teaspoonUOM));
		guaco.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tablespoonUOM));
		guaco.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2),tablespoonUOM));
		guaco.addIngredient(new Ingredient("chiles, stems and seeds removed, minced", new BigDecimal(2), eachUOM));
		guaco.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoonUOM));
		guaco.addIngredient(new Ingredient("Tomato", new BigDecimal(2), eachUOM));
		
//		log.debug("Guaco Ingredient " + guaco.getIngredient());
		guaco.getCategories().add(americanCategory);
		guaco.getCategories().add(mexicanCategory);
		
		recipes.add(guaco);
		
		
		Recipe tacos = new Recipe(); 
		tacos.setDescription("Spicy Grilled Chicken Tacos");
		tacos.setPrepTime(20);
		tacos.setCookTime(15);
		tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" + 
				"\n" + 
				"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" + 
				"\n" + 
				"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" + 
				"\n" + 
				"Spicy Grilled Chicken Tacos\n" + 
				"\n" + 
				"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" + 
				"\n" + 
				"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" + 
				"\n" + 
				"Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" + 
				"\n" + 
				"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		
		Notes tacoNote = new Notes(); 
		tacoNote.setRecipeNote("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
		tacos.setNotes(tacoNote);
		
		tacos.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUOM));
		tacos.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUOM));
		tacos.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUOM));
		tacos.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUOM));
		tacos.addIngredient(new Ingredient("Skinless boneless chicken thighs", new BigDecimal(6), eachUOM));
		tacos.addIngredient(new Ingredient("Tortillas", new BigDecimal(8), eachUOM));
		tacos.addIngredient(new Ingredient("avocados, sliced", new BigDecimal(2), ripeUOM));
		tacos.addIngredient(new Ingredient("cherry tomatoes", new BigDecimal(4), ripeUOM));
		tacos.addIngredient(new Ingredient("Onions", new BigDecimal(1.4), eachUOM));
		tacos.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUOM));
		
		tacos.getCategories().add(americanCategory);
		tacos.getCategories().add(itanialCategory);
		
		recipes.add(tacos);
		
		return recipes;
	}

}
