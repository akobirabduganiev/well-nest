package tech.nuqta.wellnest.request;

import lombok.Getter;
import lombok.Setter;
import tech.nuqta.wellnest.enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
public class CreateProfileRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Integer height;
    private Double weight;
    private String dietaryPreferences;
    private String goals;
}
