package dev.ehyeon.voice_cut_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients
public class VoiceCut1Application {

    public static void main(String[] args) {
        SpringApplication.run(VoiceCut1Application.class, args);
    }
}
