package tech.nuqta.wellnest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tech.nuqta.wellnest.dto.MealPlanDTO;
import tech.nuqta.wellnest.repository.MealPlanRepository;

@Service
@RequiredArgsConstructor
public class MealService {
    private final ChatClient chatClient;
    private final MealPlanRepository mealPlanRepository;
    @Value("classpath:/prompts/meal-plan.st")
    private Resource mealPlanPromptResource;

    public MealPlanDTO generateMealPlan() {
        return chatClient.prompt()
                .user(u -> u.text(mealPlanPromptResource))
                .call()
                .entity(MealPlanDTO.class);
    }

}
