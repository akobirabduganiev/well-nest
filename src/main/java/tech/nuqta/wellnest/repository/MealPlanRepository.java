package tech.nuqta.wellnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nuqta.wellnest.entity.MealPlan;

import java.time.LocalDate;
import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUserIdAndDate(Long userId, LocalDate date);
}