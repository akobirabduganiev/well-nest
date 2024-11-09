package tech.nuqta.wellnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nuqta.wellnest.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}