package com.odk.pjt.langchaindemo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DemoScheduler {

    private static final Logger log = LoggerFactory.getLogger(DemoScheduler.class);

    /**
     * 10초마다 실행되는 스케쥴러 예시입니다.
     * fixedRate: 이전 작업의 시작 시점으로부터 설정된 시간(ms)이 지나면 실행
     */
    @Scheduled(fixedRate = 60000)
    public void runEveryTenSeconds() {
        log.info("스케쥴러 실행 중 (매 분 정각): {}", LocalDateTime.now());
    }

    /**
     * 크론 표현식을 사용한 스케쥴러 예시입니다.
     * 매 분 0초마다 실행 (매 분 정각)
     */
    @Scheduled(cron = "0 * * * * *")
    public void runEveryMinute() {
        log.info("크론 스케쥴러 실행 중 (매 분 정각): {}", LocalDateTime.now());
    }
}
