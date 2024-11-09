package tech.nuqta.wellnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nuqta.wellnest.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}