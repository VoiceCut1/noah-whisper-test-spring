package dev.ehyeon.voice_cut_1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final OpenAIFeignClient openAIFeignClient;

    @Value("${key.openai}")
    private String apiKey;

    @Async
    public CompletableFuture<Void> analyzeTextForVoicePhishing(String text) {
        String authorizationHeader = "Bearer " + apiKey;

        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gpt-3.5-turbo");
            requestBody.put("messages", new Object[]{
                    Map.of("role", "system", "content", "You are an assistant that analyzes text for potential voice phishing scams."),
                    Map.of("role", "user", "content",
                            "Analyze the following text for voice phishing probability and return the likelihood as a percentage along with a brief explanation:\n" + text)
            });

            OpenAIResponse response = openAIFeignClient.analyzeText(authorizationHeader, requestBody);
            String result = response.choices().get(0).message().content();
            log.info("Analysis result: {}", result);
        } catch (Exception e) {
            log.error("Error during analysis", e);
        }

        return CompletableFuture.completedFuture(null);
    }
}
