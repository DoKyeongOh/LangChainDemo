package com.odk.pjt.langchaindemo.chat.dto;

import java.util.List;

/**
 * [P4.0] 채팅 응답 DTO
 */
public class ChatResponse {

    private String answer;
    private List<Long> sources; // 근거 문서 ID 리스트 (documentId)
    private String sessionId;

    public ChatResponse() {
    }

    public ChatResponse(String answer, List<Long> sources, String sessionId) {
        this.answer = answer;
        this.sources = sources;
        this.sessionId = sessionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Long> getSources() {
        return sources;
    }

    public void setSources(List<Long> sources) {
        this.sources = sources;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "ChatResponse{" +
                "answer='" + answer + '\'' +
                ", sources=" + sources +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
