// Meal.java
package tech.nuqta.wellnest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * The Meal class represents an individual meal in a meal plan.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    private String mealType; // e.g., Breakfast, Lunch, Dinner

    private String description; // Recipe name or brief description

    private Integer calories;

    private Integer protein; // in grams

    private Integer fat; // in grams

    private Integer carbs; // in grams

    @Column(length = 5000)
    private String instructions; // Cooking instructions

    private String imageUrl; // URL to the meal image

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe; // Link to the Recipe entity
}
