package tech.nuqta.wellnest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularStep {
    private int number;
    private String step;
}
