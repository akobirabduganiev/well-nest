package tech.nuqta.wellnest.dto;

import lombok.Data;

import java.util.List;

@Data
public class MealPlanResponseDTO {
    private Long id;
    private String date;
    private Integer totalCalories;
    private List<MealDTO> meals;
}
