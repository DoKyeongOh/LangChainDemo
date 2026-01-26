package com.odk.pjt.langchaindemo.chat.model;

import java.time.LocalDateTime;

/**
 * 사용자 질의 히스토리 (JSON 파일 저장용)
 */
public class ChatHistoryEntry {

    private String sessionId;
    private String query;
    private LocalDateTime timestamp;
    private String response;

    public ChatHistoryEntry() {
    }

    public ChatHistoryEntry(String sessionId, String query, LocalDateTime timestamp, String response) {
        this.sessionId = sessionId;
        this.query = query;
        this.timestamp = timestamp;
        this.response = response;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ChatHistoryEntry{" +
                "sessionId='" + sessionId + '\'' +
                ", query='" + query + '\'' +
                ", timestamp=" + timestamp +
                ", response='" + response + '\'' +
                '}';
    }
}
