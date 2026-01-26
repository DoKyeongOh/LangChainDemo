package com.odk.pjt.langchaindemo.chat.model;

import java.time.LocalDateTime;

/**
 * 사용자 질의 세션 (JSON 파일 저장용)
 */
public class ChatSession {

    private String id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime lastActiveAt;

    public ChatSession() {
    }

    public ChatSession(String id, String title, LocalDateTime createdAt, LocalDateTime lastActiveAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.lastActiveAt = lastActiveAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(LocalDateTime lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

    @Override
    public String toString() {
        return "ChatSession{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", lastActiveAt=" + lastActiveAt +
                '}';
    }
}
