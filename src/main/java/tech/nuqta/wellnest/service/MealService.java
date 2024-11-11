package tech.nuqta.wellnest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tech.nuqta.wellnest.dto.OneMealName;
import tech.nuqta.wellnest.repository.MealPlanRepository;

@Service
@RequiredArgsConstructor
public class MealService {
    private final ChatClient chatClient;
    private final MealPlanRepository mealPlanRepository;
    @Value("classpath:/prompts/meal-plan.st")
    private Resource mealPlanPromptResource;

    @Value("classpath:/prompts/meal-info.st")
    private Resource mealInfoPromptResource;

    public String generateMealPlan() {
        return chatClient.prompt()
                .user(u -> u.text(mealPlanPromptResource))
                .call()
                .content();
    }

    public String getMealInfo(OneMealName mealName) {
        return chatClient.prompt()
                .user(u -> u.text(mealInfoPromptResource).param("mealName",mealName.getName()))
                .call()
                .content();

    }
}
