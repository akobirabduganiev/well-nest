package tech.nuqta.wellnest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import tech.nuqta.wellnest.config.SpoonacularConfig;
import tech.nuqta.wellnest.dto.*;
import tech.nuqta.wellnest.entity.*;
import tech.nuqta.wellnest.repository.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpoonacularService {
    private final SpoonacularConfig config;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final MealPlanRepository mealPlanRepository;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    public MealPlanResponseDTO generateMealPlanForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Calculate target calories based on user data
        int targetCalories = calculateCalories(user);

        // Build Spoonacular API URL
        String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                .path("/mealplanner/generate")
                .queryParam("apiKey", config.getApiKey())
                .queryParam("timeFrame", "week") // or "day"
                .queryParam("targetCalories", targetCalories)
                .queryParam("diet", user.getProfile().getDietaryPreferences()) // e.g., "vegetarian"
                .queryParam("exclude", "shellfish") // example exclusion, adjust as needed
                .toUriString();

        ResponseEntity<SpoonacularMealPlanResponse> response = restTemplate.getForEntity(url, SpoonacularMealPlanResponse.class);

        log.debug("Spoonacular API Response Status: {}", response.getStatusCode());
        log.debug("Spoonacular API Response Body: {}", response.getBody());

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            SpoonacularMealPlanResponse mealPlanResponse = response.getBody();
            // Map to MealPlan entity
            MealPlan mealPlan = mapToMealPlan(mealPlanResponse, user);
            // Save to database
            mealPlan = mealPlanRepository.save(mealPlan);
            // Map to Response DTO
            return mapToResponseDTO(mealPlan);
        } else {
            throw new RuntimeException("Failed to generate meal plan: " + response.getStatusCode());
        }
    }

    private int calculateCalories(User user) {
        // Placeholder calorie calculation; replace with a proper formula
        // Example: Harris-Benedict equation or any other suitable method
        return (int) (user.getProfile().getWeight() * 10); // Simple placeholder
    }

    private MealPlan mapToMealPlan(SpoonacularMealPlanResponse response, User user) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setDate(LocalDate.now());
        mealPlan.setTotalCalories(response.getCalories());
        mealPlan.setUser(user);
        mealPlan.setMeals(new ArrayList<>());

        for (SpoonacularMeal spoonMeal : response.getMeals()) {
            Meal meal = new Meal();
            meal.setMealType(determineMealType(spoonMeal.getTitle()));
            meal.setDescription(spoonMeal.getTitle());
            meal.setCalories(response.getCalories() / response.getMeals().size()); // Simplistic distribution
            meal.setProtein(response.getProtein() / response.getMeals().size());
            meal.setFat(response.getFat() / response.getMeals().size());
            meal.setCarbs(response.getCarbs() / response.getMeals().size());
            meal.setImageUrl(spoonMeal.getImage());

            // Fetch instructions
            String instructions = fetchInstructions(spoonMeal.getId());
            meal.setInstructions(instructions);

            // Handle Recipe Entity
            Recipe recipe = fetchOrCreateRecipe(spoonMeal);
            meal.setRecipe(recipe);
            meal.setMealPlan(mealPlan);

            mealPlan.getMeals().add(meal);
        }

        return mealPlan;
    }

    private String determineMealType(String mealTitle) {
        // Simple logic based on meal title keywords
        String titleLower = mealTitle.toLowerCase();
        if (titleLower.contains("breakfast")) {
            return "Breakfast";
        } else if (titleLower.contains("lunch")) {
            return "Lunch";
        } else if (titleLower.contains("dinner")) {
            return "Dinner";
        } else {
            return "Snack";
        }
    }

    private String fetchInstructions(int recipeId) {
        // Build Spoonacular API URL for instructions
        String url = UriComponentsBuilder.fromHttpUrl(config.getBaseUrl())
                .path("/recipes/" + recipeId + "/analyzedInstructions")
                .queryParam("apiKey", config.getApiKey())
                .toUriString();

        ResponseEntity<SpoonacularRecipeInstructions[]> response = restTemplate.getForEntity(url, SpoonacularRecipeInstructions[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && response.getBody().length > 0) {
            SpoonacularRecipeInstructions instructions = response.getBody()[0];
            StringBuilder instructionText = new StringBuilder();
            for (SpoonacularStep step : instructions.getSteps()) {
                instructionText.append(step.getNumber()).append(". ").append(step.getStep()).append("\n");
            }
            return instructionText.toString();
        } else {
            return "No instructions available.";
        }
    }

    private Recipe fetchOrCreateRecipe(SpoonacularMeal spoonMeal) {
        Optional<Recipe> optionalRecipe = recipeRepository.findByTitle(spoonMeal.getTitle());
        if (optionalRecipe.isPresent()) {
            return optionalRecipe.get();
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(spoonMeal.getTitle());
        recipe.setImageUrl(spoonMeal.getImage());

        // Assign a default category or based on some logic
        Optional<Category> categoryOpt = categoryRepository.findByName("General");
        if (categoryOpt.isPresent()) {
            recipe.setCategory(categoryOpt.get());
        } else {
            // Create a default category if not exists
            Category category = new Category();
            category.setName("General");
            category = categoryRepository.save(category);
            recipe.setCategory(category);
        }

        return recipeRepository.save(recipe);
    }

    private MealPlanResponseDTO mapToResponseDTO(MealPlan mealPlan) {
        MealPlanResponseDTO dto = new MealPlanResponseDTO();
        dto.setId(mealPlan.getId());
        dto.setDate(mealPlan.getDate().toString());
        dto.setTotalCalories(mealPlan.getTotalCalories());

        List<MealDTO> mealDTOs = new ArrayList<>();
        for (Meal meal : mealPlan.getMeals()) {
            MealDTO mealDTO = new MealDTO();
            mealDTO.setMealType(meal.getMealType());
            mealDTO.setDescription(meal.getDescription());
            mealDTO.setCalories(meal.getCalories());
            mealDTO.setProtein(meal.getProtein());
            mealDTO.setFat(meal.getFat());
            mealDTO.setCarbs(meal.getCarbs());
            mealDTO.setInstructions(meal.getInstructions());
            mealDTO.setImageUrl(meal.getImageUrl());
            mealDTOs.add(mealDTO);
        }

        dto.setMeals(mealDTOs);
        return dto;
    }
}
