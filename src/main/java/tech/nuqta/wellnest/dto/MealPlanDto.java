package tech.nuqta.wellnest.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link tech.nuqta.wellnest.entity.MealPlan}
 */
@Value
public class MealPlanDto implements Serializable {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDate date;
    Integer totalCalories;
    UserDto user;
    List<MealDto> meals;
}