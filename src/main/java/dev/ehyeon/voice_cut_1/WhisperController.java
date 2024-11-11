package dev.ehyeon.voice_cut_1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/whisper")
@RequiredArgsConstructor
public class WhisperController {

    private final WhisperService whisperService;
    private final OpenAIService openAIService;

    @PostMapping("/transcribe")
    public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file) {
        // 비동기로 Whisper와 OpenAI 호출
        whisperService.transcribeAudio(file)
                .thenCompose(openAIService::analyzeTextForVoicePhishing);

        // 즉시 응답 반환
        return ResponseEntity.ok("Processing started.");
    }
}
