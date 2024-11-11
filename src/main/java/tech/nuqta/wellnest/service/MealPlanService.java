// MealPlanService.java
package tech.nuqta.wellnest.service;

import tech.nuqta.wellnest.dto.MealPlanDto;
import tech.nuqta.wellnest.entity.MealPlan;
import tech.nuqta.wellnest.entity.Profile;

import java.util.List;

public interface MealPlanService {
    List<MealPlanDto> generateMealPlan(Profile profile, int days);
    List<MealPlanDto> getMealPlansByProfile(Profile profile);


}
