package dev.ehyeon.voice_cut_1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "whisperClient", url = "https://api.openai.com/v1/audio/transcriptions")
public interface WhisperFeignClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    WhisperResponse transcribeAudio(
            @RequestHeader("Authorization") String authorization,
            @RequestPart("file") MultipartFile file,
            @RequestPart("model") String model
    );
}
