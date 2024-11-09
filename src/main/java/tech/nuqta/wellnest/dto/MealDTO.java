package tech.nuqta.wellnest.dto;

import lombok.Data;

@Data
public class MealDTO {
    private String mealType; // Breakfast, Lunch, Dinner, Snack
    private String description; // Recipe name or description
    private Integer calories;
    private Integer protein;
    private Integer fat;
    private Integer carbs;
    private String instructions;
    private String imageUrl;
}
