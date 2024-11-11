package tech.nuqta.wellnest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nuqta.wellnest.common.CurrentUser;
import tech.nuqta.wellnest.dto.MealPlanDto;
import tech.nuqta.wellnest.service.MealPlanService;

import java.util.List;

/**
 * The MealController class provides REST endpoints for managing meal plans.
 * It allows users to generate a three-day meal plan and fetch all meal plans
 * associated with the current user's profile.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meals")
public class MealController {
    private final MealPlanService mealPlanService;

    /**
     * Generates a three-day meal plan for the current user based on their profile.
     *
     * @return a ResponseEntity containing a list of MealPlanDto objects representing the three-day meal plan
     */
    @PostMapping("/generate/3days")
    public ResponseEntity<List<MealPlanDto>> generateThreeDayMealPlan() {
        var currentUser = CurrentUser.getCurrentUser();

        List<MealPlanDto> mealPlans = mealPlanService.generateMealPlan(currentUser.getProfile(), 3);
        return ResponseEntity.ok(mealPlans);
    }

    /**
     * Retrieves all meal plans associated with the current user's profile.
     *
     * @return a ResponseEntity containing a list of MealPlanDto objects
     */
    @GetMapping("/all")
    public ResponseEntity<List<MealPlanDto>> getAllMealPlans() {
        var currentUser = CurrentUser.getCurrentUser();
        List<MealPlanDto> mealPlans = mealPlanService.getMealPlansByProfile(currentUser.getProfile());
        return ResponseEntity.ok(mealPlans);
    }

}
