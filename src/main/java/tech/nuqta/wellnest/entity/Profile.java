// Profile.java
package tech.nuqta.wellnest.entity;

import jakarta.persistence.*;
import lombok.*;
import tech.nuqta.wellnest.enums.DietaryPreference;
import tech.nuqta.wellnest.enums.Gender;
import tech.nuqta.wellnest.enums.Goal;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {
    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer height; // in centimeters
    private Double weight; // in kilograms

    @Enumerated(EnumType.STRING)
    private DietaryPreference dietaryPreference; // e.g., Vegetarian, Vegan

    @Enumerated(EnumType.STRING)
    private Goal goals; // e.g., Weight Loss, Maintenance

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
