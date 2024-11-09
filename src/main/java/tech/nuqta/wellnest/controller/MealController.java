package tech.nuqta.wellnest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nuqta.wellnest.service.MealService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meals")
public class MealController {
    private final MealService mealService;

    @GetMapping("/generate-meal-plan")
    public String generateMealPlan() {
        return mealService.generateMealPlan();
    }
}
