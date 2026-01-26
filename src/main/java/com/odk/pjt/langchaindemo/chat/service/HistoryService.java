package com.odk.pjt.langchaindemo.chat.service;

import com.odk.pjt.langchaindemo.chat.model.ChatHistoryEntry;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * [P4.0] 히스토리 관리 서비스 (JSON 파일 기반)
 */
@Service
public class HistoryService {

    // TODO: JSON 파일 경로 설정

    // TODO: 히스토리 항목 추가
    public void addHistory(ChatHistoryEntry entry) {
    }

    // TODO: 세션별 히스토리 조회
    public List<ChatHistoryEntry> getHistoryBySessionId(String sessionId) {
        return List.of();
    }

    // TODO: 세션 히스토리 삭제
    public void deleteHistoryBySessionId(String sessionId) {
    }
}
