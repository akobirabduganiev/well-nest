package tech.nuqta.wellnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nuqta.wellnest.entity.MealPlan;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
}