package tech.nuqta.wellnest.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import tech.nuqta.wellnest.enums.DietaryPreference;
import tech.nuqta.wellnest.enums.Gender;
import tech.nuqta.wellnest.enums.Goal;

import java.time.LocalDate;

@Getter
@Setter
public class CreateProfileRequest {
    @NotNull(message = "Please provide your first name")
    private String firstName;
    @NotNull(message = "Please provide your last name")
    private String lastName;
    @NotNull(message = "Please provide your date of birth")
    private LocalDate dateOfBirth;
    @NotNull(message = "Please provide gender")
    private Gender gender;
    @NotNull(message = "Please provide your height")
    private Integer height;
    @NotNull(message = "Please provide your weight")
    private Double weight;
    @NotNull(message = "Please provide your dietary preference")
    private DietaryPreference dietaryPreference;
    @NotNull(message = "Please provide your goal")
    private Goal goals;
}
