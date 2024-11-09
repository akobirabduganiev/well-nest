package tech.nuqta.wellnest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.nuqta.wellnest.dto.MealPlanResponseDTO;
import tech.nuqta.wellnest.entity.MealPlan;
import tech.nuqta.wellnest.service.MealService;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meals")
public class MealController {
    private final MealService mealService;


    /**
     * Endpoint to generate a new meal plan for the authenticated user.
     */
    @PostMapping("/generate")
    public ResponseEntity<MealPlanResponseDTO> generateMealPlan() {
        return ResponseEntity.ok(mealService.generateMealPlan());
    }

    /**
     * Endpoint to retrieve meal plans for a user on a specific date.
     */
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MealPlan>> getMealPlansByDate(@PathVariable String date, Principal principal) {
        LocalDate localDate = LocalDate.parse(date); // Ensure the date format is correct
        List<MealPlan> mealPlans = mealService.getMealPlansForUser(localDate);
        return ResponseEntity.ok(mealPlans);
    }

}
