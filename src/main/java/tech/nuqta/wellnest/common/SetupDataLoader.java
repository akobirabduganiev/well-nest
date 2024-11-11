// SetupDataLoader.java
package tech.nuqta.wellnest.common;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.nuqta.wellnest.entity.Meal;
import tech.nuqta.wellnest.entity.Recipe;
import tech.nuqta.wellnest.entity.Role;
import tech.nuqta.wellnest.enums.DietaryPreference;
import tech.nuqta.wellnest.enums.RoleName;
import tech.nuqta.wellnest.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final RecipeRepository recipeRepository;
    private final MealRepository mealRepository;

    public SetupDataLoader(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           RecipeRepository recipeRepository,
                           MealPlanRepository mealPlanRepository,
                           MealRepository mealRepository) {
        this.roleRepository = roleRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Step 1: Populate Roles
        populateRoles();


        // Step 2: Populate Recipes and Meals
        if (recipeRepository.count() == 0) {
            List<Recipe> recipes = generateRecipes();
            recipeRepository.saveAll(recipes);
            System.out.println("Successfully added 50 recipes to the database.");
        } else {
            System.out.println("Recipes already exist in the database. Skipping recipe population.");
        }

        if (mealRepository.count() == 0) {
            List<Meal> meals = generateMeals();
            mealRepository.saveAll(meals);
            System.out.println("Successfully added 50 meals to the database.");
        } else {
            System.out.println("Meals already exist in the database. Skipping meal population.");
        }
    }

    private void populateRoles() {
        if (roleRepository.count() == 0) {
            Role userRole = Role.builder().name(RoleName.USER).build();
            Role adminRole = Role.builder().name(RoleName.ADMIN).build();
            roleRepository.saveAll(Arrays.asList(userRole, adminRole));
            System.out.println("Default roles USER and ADMIN have been created.");
        } else {
            System.out.println("Roles already exist in the database. Skipping role population.");
        }
    }

    private List<Recipe> generateRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        // Sample list of 50 recipes with diverse dietary preferences
        for (int i = 1; i <= 50; i++) {
            DietaryPreference preference = switch (i % 5) {
                case 0 -> DietaryPreference.VEGAN;
                case 1 -> DietaryPreference.VEGETARIAN;
                case 2 -> DietaryPreference.GLUTEN_FREE;
                case 3 -> DietaryPreference.KETO;
                default -> DietaryPreference.NONE;
            };

            Recipe recipe = Recipe.builder()
                    .title("Recipe " + i)
                    .description("Delicious and healthy Recipe " + i)
                    .ingredients("Ingredient1, Ingredient2, Ingredient3, Ingredient4")
                    .instructions("Step 1: Do something.\nStep 2: Do something else.\nStep 3: Enjoy your meal.")
                    .calories(300 + i) // Sample calorie count
                    .protein(10 + (i % 20)) // Sample protein in grams
                    .fat(5 + (i % 15)) // Sample fat in grams
                    .carbs(40 + (i % 50)) // Sample carbs in grams
                    .imageUrl("https://example.com/recipe" + i + ".jpg") // Placeholder image URL
                    .dietaryPreference(preference)
                    .build();
            recipes.add(recipe);
        }

        return recipes;
    }

    private List<Meal> generateMeals() {
        List<Meal> meals = new ArrayList<>();

        List<Recipe> allRecipes = recipeRepository.findAll();
        if (allRecipes.size() < 50) {
            throw new RuntimeException("Not enough recipes to generate meals. Required: 50, Available: " + allRecipes.size());
        }

        String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Snack1", "Snack2"};

        for (int i = 0; i < 50; i++) {
            Recipe recipe = allRecipes.get(i);

            String mealType = mealTypes[i % mealTypes.length];

            Meal meal = Meal.builder()
                    .mealType(mealType)
                    .description(recipe.getTitle())
                    .calories(recipe.getCalories())
                    .protein(recipe.getProtein())
                    .fat(recipe.getFat())
                    .carbs(recipe.getCarbs())
                    .instructions(recipe.getInstructions())
                    .imageUrl(recipe.getImageUrl())
                    .recipe(recipe)
                    .mealPlan(null) // No MealPlan association
                    .build();

            meals.add(meal);
        }

        return meals;
    }
}
