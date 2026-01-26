package com.odk.pjt.langchaindemo.chat.service;

import com.odk.pjt.langchaindemo.chat.model.ChatSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [P4.0] 세션 관리 서비스 (JSON 파일 기반)
 */
@Service
public class SessionService {

    // TODO: JSON 파일 경로 설정

    // TODO: 새 세션 생성
    public ChatSession createSession(String title) {
        return null;
    }

    // TODO: 세션 조회
    public Optional<ChatSession> getSession(String sessionId) {
        return Optional.empty();
    }

    // TODO: 모든 세션 조회
    public List<ChatSession> getAllSessions() {
        return List.of();
    }

    // TODO: 세션 활동 시간 업데이트
    public void updateLastActiveAt(String sessionId) {
    }

    // TODO: 세션 삭제
    public void deleteSession(String sessionId) {
    }
}
