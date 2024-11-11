package dev.ehyeon.voice_cut_1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class WhisperService {

    private final WhisperFeignClient whisperFeignClient;

    @Value("${key.openai}")
    private String apiKey;

    public String transcribeAudio(MultipartFile audioFile) {
        String authorizationHeader = "Bearer " + apiKey;

        return whisperFeignClient.transcribeAudio(authorizationHeader, audioFile, "whisper-1");
    }
}
