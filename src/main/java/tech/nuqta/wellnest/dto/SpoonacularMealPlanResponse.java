package tech.nuqta.wellnest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularMealPlanResponse {
    private String type; // "day" or "week"
    private int calories;
    private int protein;
    private int fat;
    private int carbs;
    private List<SpoonacularMeal> meals;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class SpoonacularMeal {
    private int id;
    private String title;
    private String imageType;
    private String image;
    private int readyInMinutes;
    private int servings;
}
