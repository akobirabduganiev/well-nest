package tech.nuqta.wellnest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activity_histories")
public class ActivityHistory extends BaseEntity {

    private String activityType; // e.g., "Viewed Meal Plan", "Viewed Recipe"

    private LocalDateTime timestamp;

    private String details; // Additional information about the activity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
