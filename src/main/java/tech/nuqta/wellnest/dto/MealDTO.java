package tech.nuqta.wellnest.dto;

import lombok.Data;

@Data
public class MealDTO {
    private String type;
    private String name;
    private String calories;
    private String weight;
    private NutrientsDTO nutrients;
}