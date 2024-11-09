package tech.nuqta.wellnest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tech.nuqta.wellnest.common.CurrentUser;
import tech.nuqta.wellnest.entity.MealPlan;
import tech.nuqta.wellnest.entity.Profile;
import tech.nuqta.wellnest.entity.User;

@Service
@RequiredArgsConstructor
public class MealService {
    private final ChatClient chatClient;
    @Value("classpath:/prompts/meal-plan.st")
    private Resource generateMealPlanResource;

    public String generateMealPlan() {
        String prompt = "Using the following profile information, please generate a detailed 1-day meal plan:\n\n" +
                "- First Name: {firstName}\n" +
                "- Last Name: {lastName}\n" +
                "- Date of Birth: {dateOfBirth}\n" +
                "- Gender: {gender}\n" +
                "- Height: {height} cm\n" +
                "- Weight: {weight} kg\n" +
                "- Dietary Preferences: {dietaryPreferences}\n" +
                "- Goals: {goals}\n\n" +
                "Please provide the response in a JSON format with fields for total calories, meal details, and nutritional information.\n" +
                "Include each meal type (Breakfast, Lunch, Dinner, Snack) with fields for calories, protein, fat, carbohydrates, and cooking instructions.\n\n" +
                "If a field is unknown, respond with 'null' or an empty string. If you cannot provide an answer, respond with 'I'm sorry, but I don't know the answer to that. Return only json other comments not allowed.'";

        User user = CurrentUser.getCurrentUser();
        Profile profile = user.getProfile();
        return chatClient.prompt()
                .user(u -> u.text(prompt)
                        .param("firstName", profile.getFirstName())
                        .param("lastName", profile.getLastName())
                        .param("dateOfBirth", profile.getDateOfBirth())
                        .param("gender", profile.getGender())
                        .param("height", profile.getHeight())
                        .param("weight", profile.getWeight())
                        .param("dietaryPreferences", profile.getDietaryPreferences())
                        .param("goals", profile.getGoals())
                )
                .call()
                .content();
    }

}
