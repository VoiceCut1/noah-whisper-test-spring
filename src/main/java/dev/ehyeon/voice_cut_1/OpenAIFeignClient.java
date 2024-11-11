package dev.ehyeon.voice_cut_1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "openAIClient", url = "https://api.openai.com/v1/chat/completions")
public interface OpenAIFeignClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    String analyzeText(
            @RequestHeader("Authorization") String authorization,
            @RequestBody Map<String, Object> requestBody
    );
}
