package tech.nuqta.wellnest.dto;

import lombok.Data;

import java.util.List;

@Data
public class DayPlanDTO {
    private String day;
    private List<MealDTO> meals;
}