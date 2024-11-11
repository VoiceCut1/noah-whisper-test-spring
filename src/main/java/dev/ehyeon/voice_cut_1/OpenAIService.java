package dev.ehyeon.voice_cut_1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final OpenAIFeignClient openAIFeignClient;

    @Value("${key.openai}")
    private String apiKey;

    public String analyzeTextForVoicePhishing(String text) {
        String authorizationHeader = "Bearer " + apiKey;

        // GPT 모델 요청 데이터 구성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
                Map.of("role", "system", "content", "You are an assistant that analyzes text for potential voice phishing scams."),
                Map.of("role", "user", "content",
                        "Analyze the following text for voice phishing probability and return the likelihood as a percentage along with a brief explanation:\n" + text)
        });

        OpenAIResponse openAIResponse = openAIFeignClient.analyzeText(authorizationHeader, requestBody);

        return openAIResponse.choices().get(0).message().content();
    }
}
