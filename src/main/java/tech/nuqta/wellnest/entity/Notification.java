// Notification.java
package tech.nuqta.wellnest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

    private String type; // e.g., "Meal Reminder", "Shopping Reminder"

    private String subject;

    private String message;

    private LocalDateTime scheduledTime;

    private boolean sent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
