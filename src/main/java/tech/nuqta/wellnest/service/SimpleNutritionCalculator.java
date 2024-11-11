// SimpleNutritionCalculator.java
package tech.nuqta.wellnest.service;

import org.springframework.stereotype.Service;
import tech.nuqta.wellnest.enums.Gender;
import tech.nuqta.wellnest.entity.Profile;

import java.time.LocalDate;
import java.time.Period;

@Service
public class SimpleNutritionCalculator {

    /**
     * Calculates daily calorie needs based on the Mifflin-St Jeor Equation.
     *
     * @param profile The user's profile.
     * @return Estimated daily calories.
     */
    public double calculateDailyCalories(Profile profile) {
        double bmr;
        int age = calculateAge(profile.getDateOfBirth());

        if (profile.getGender() == Gender.MALE) {
            bmr = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * age + 5;
        } else {
            bmr = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * age - 161;
        }

        // Assuming moderate activity level for simplicity
        double tdee = bmr * 1.55;

        // Adjust based on goal
        return switch (profile.getGoals()) {
            case WEIGHT_LOSS -> tdee - 500; // Approx. 0.5 kg weight loss per week
            case WEIGHT_GAIN -> tdee + 500; // Approx. 0.5 kg weight gain per week
            default -> tdee;
        };
    }

    private int calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return 30; // Default age
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
