package tech.nuqta.wellnest.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link tech.nuqta.wellnest.entity.User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long profileId;
}