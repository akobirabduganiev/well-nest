package tech.nuqta.wellnest.dto;

import lombok.Data;

import java.util.List;

@Data
public class MealPlanDTO {
    private List<DayPlanDTO> mealPlan;
}