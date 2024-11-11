package dev.ehyeon.voice_cut_1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime(); // 시작 시간
        Object result = joinPoint.proceed(); // 메서드 실행
        long endTime = System.nanoTime(); // 종료 시간

        log.info("Execution time of {}: {} ms",
                joinPoint.getSignature(),
                (endTime - startTime) / 1_000_000);

        return result;
    }
}
