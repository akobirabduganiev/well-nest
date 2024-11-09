package tech.nuqta.wellnest.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpoonacularConfig {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Value("${spoonacular.api.base-url}")
    private String baseUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
