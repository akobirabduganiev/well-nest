package tech.nuqta.wellnest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OneMealName {
    @NotNull
   private String name;
}
