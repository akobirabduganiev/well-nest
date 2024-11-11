package tech.nuqta.wellnest.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link tech.nuqta.wellnest.entity.Meal}
 */
@Value
public class MealDto implements Serializable {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String mealType;
    String description;
    Integer calories;
    Integer protein;
    Integer fat;
    Integer carbs;
    String instructions;
    String imageUrl;
    Long recipeId;
}