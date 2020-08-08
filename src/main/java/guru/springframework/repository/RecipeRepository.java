package guru.springframework.repository;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
