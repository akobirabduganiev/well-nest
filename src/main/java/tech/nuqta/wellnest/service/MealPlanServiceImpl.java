// MealPlanServiceImpl.java
package tech.nuqta.wellnest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.nuqta.wellnest.dto.MealDto;
import tech.nuqta.wellnest.dto.MealPlanDto;
import tech.nuqta.wellnest.dto.UserDto;
import tech.nuqta.wellnest.entity.*;
import tech.nuqta.wellnest.enums.DietaryPreference;
import tech.nuqta.wellnest.repository.MealPlanRepository;
import tech.nuqta.wellnest.repository.MealRepository;
import tech.nuqta.wellnest.repository.RecipeRepository;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    private final SimpleNutritionCalculator nutritionCalculator;
    private final RecipeRepository recipeRepository;
    private final MealPlanRepository mealPlanRepository;
    private final MealRepository mealRepository;

    public MealPlanServiceImpl(SimpleNutritionCalculator nutritionCalculator,
                               RecipeRepository recipeRepository,
                               MealPlanRepository mealPlanRepository,
                               MealRepository mealRepository) {
        this.nutritionCalculator = nutritionCalculator;
        this.recipeRepository = recipeRepository;
        this.mealPlanRepository = mealPlanRepository;
        this.mealRepository = mealRepository;
    }


    @Override
    @Transactional
    public List<MealPlanDto> generateMealPlan(Profile profile, int days) {
        List<MealPlan> mealPlans = new ArrayList<>();

        // Calculate daily calories
        double dailyCalories = nutritionCalculator.calculateDailyCalories(profile);
        System.out.println("Daily Caloric Needs: " + dailyCalories);

        // Filter recipes by dietary preference
        List<Recipe> filteredRecipes = recipeRepository.findAll().stream()
                .filter(recipe -> matchesDietaryPreference(recipe, profile.getDietaryPreference()))
                .collect(Collectors.toList());

        if (filteredRecipes.size() < 5) {
            throw new RuntimeException("Not enough recipes to generate a meal plan.");
        }

        for (int day = 0; day < days; day++) {
            // Assign a date for the meal plan
            LocalDate planDate = LocalDate.now().plusDays(day);

            // Shuffle recipes to ensure variety
            Collections.shuffle(filteredRecipes);

            // Select 5 recipes for the day
            List<Recipe> selectedRecipes = selectRandomRecipes(filteredRecipes);

            // Define meal types
            String[] mealTypes = {"Breakfast", "Lunch", "Dinner"};

            // Create MealPlan entity
            MealPlan mealPlan = MealPlan.builder()
                    .user(profile.getUser())
                    .date(planDate)
                    .totalCalories((int) dailyCalories)
                    .meals(new ArrayList<>())
                    .build();

            // Create and assign meals
            for (int i = 0; i < mealTypes.length; i++) {
                String mealType = mealTypes[i];
                Recipe recipe = selectedRecipes.get(i);

                Meal meal = Meal.builder()
                        .mealPlan(mealPlan)
                        .recipe(recipe)
                        .mealType(mealType)
                        .description(recipe.getTitle())
                        .calories(recipe.getCalories())
                        .protein(recipe.getProtein())
                        .fat(recipe.getFat())
                        .carbs(recipe.getCarbs())
                        .instructions(recipe.getInstructions())
                        .imageUrl(recipe.getImageUrl())
                        .build();

                mealPlan.getMeals().add(meal);
            }

            // Persist MealPlan and Meals
            mealPlanRepository.save(mealPlan);
            mealRepository.saveAll(mealPlan.getMeals());

            mealPlans.add(mealPlan);
        }

        return mealPlans.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MealPlanDto> getMealPlansByProfile(Profile profile) {
        return mealPlanRepository.findAllByUser(profile.getUser()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Selects a specified number of random recipes from the filtered list.
     *
     * @param recipes The list of filtered recipes.
     * @return A list of selected Recipe entities.
     */
    private List<Recipe> selectRandomRecipes(List<Recipe> recipes) {
        return recipes.stream().limit(5).collect(Collectors.toList());
    }

    /**
     * Checks if a recipe matches the user's dietary preference.
     *
     * @param recipe              The recipe to check.
     * @param userDietaryPreference The user's dietary preference.
     * @return True if the recipe matches the preference or if the user has no preference, else false.
     */
    private boolean matchesDietaryPreference(Recipe recipe, DietaryPreference userDietaryPreference) {
        if (userDietaryPreference == DietaryPreference.NONE) {
            return true; // No dietary restrictions
        }

        return recipe.getDietaryPreference() == userDietaryPreference;
    }

    private MealPlanDto convertToDto(MealPlan mealPlan) {
        return new MealPlanDto(
                mealPlan.getId(),
                mealPlan.getCreatedAt(),
                mealPlan.getUpdatedAt(),
                mealPlan.getDate(),
                mealPlan.getTotalCalories(),
                new UserDto(
                        mealPlan.getUser().getId(),
                        mealPlan.getUser().getEmail(),
                        mealPlan.getUser().getCreatedAt(),
                        mealPlan.getUser().getUpdatedAt(),
                        mealPlan.getUser().getProfile().getId()
                ),
                mealPlan.getMeals().stream()
                        .map(meal -> new MealDto(
                                meal.getId(),
                                meal.getCreatedAt(),
                                meal.getUpdatedAt(),
                                meal.getMealType(),
                                meal.getDescription(),
                                meal.getCalories(),
                                meal.getProtein(),
                                meal.getFat(),
                                meal.getCarbs(),
                                meal.getInstructions(),
                                meal.getImageUrl(),
                                meal.getRecipe().getId()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
