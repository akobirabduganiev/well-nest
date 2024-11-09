package tech.nuqta.wellnest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import tech.nuqta.wellnest.enums.Gender;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProfileDto(
        Long id,
        Long userId,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        Gender gender,
        Integer height,
        Double weight,
        String dietaryPreferences,
        String goals
) {
}
