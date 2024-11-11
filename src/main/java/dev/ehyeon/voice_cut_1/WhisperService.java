package dev.ehyeon.voice_cut_1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhisperService {

    private final WhisperFeignClient whisperFeignClient;

    @Value("${key.openai}")
    private String apiKey;

    @Async
    public CompletableFuture<String> transcribeAudio(MultipartFile audioFile) {
        String authorizationHeader = "Bearer " + apiKey;

        try {
            String text = whisperFeignClient.transcribeAudio(authorizationHeader, audioFile, "whisper-1").text();
            log.info("Transcribed text: {}", text);
            return CompletableFuture.completedFuture(text);
        } catch (Exception e) {
            log.error("Error during transcription", e);
            return CompletableFuture.failedFuture(e);
        }
    }
}
