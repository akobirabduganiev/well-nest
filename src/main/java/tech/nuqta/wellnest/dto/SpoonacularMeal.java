package tech.nuqta.wellnest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularMeal {
    private int id;
    private String title;
    private String imageType;
    private String image;
    private int readyInMinutes;
    private int servings;
}
