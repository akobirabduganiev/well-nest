package tech.nuqta.wellnest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularRecipeInstructions {
    private List<SpoonacularStep> steps;
}

