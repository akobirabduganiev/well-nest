package tech.nuqta.wellnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nuqta.wellnest.entity.Recipe;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByTitle(String title);
}