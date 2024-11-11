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
        long startTime = System.nanoTime(); // 시작 시간 기록

        String authorizationHeader = "Bearer " + apiKey;

        try {
            String text = whisperFeignClient.transcribeAudio(authorizationHeader, audioFile, "whisper-1").text();
            long endTime = System.nanoTime(); // 종료 시간 기록
            log.info("Transcription completed. Time taken: {} ms", (endTime - startTime) / 1_000_000);
            return CompletableFuture.completedFuture(text);
        } catch (Exception e) {
            long endTime = System.nanoTime(); // 종료 시간 기록
            log.error("Error during transcription. Time taken: {} ms", (endTime - startTime) / 1_000_000);
            return CompletableFuture.failedFuture(e);
        }
    }
}
