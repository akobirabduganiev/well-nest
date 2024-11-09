package tech.nuqta.wellnest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealPlanService {
    private final ChatClient chatClient;



}
