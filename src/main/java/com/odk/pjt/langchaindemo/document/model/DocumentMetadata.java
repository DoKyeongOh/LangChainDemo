package com.odk.pjt.langchaindemo.document.model;

import java.time.LocalDateTime;

/**
 * 규정 문서 메타데이터 (RDB 저장용)
 */
public class DocumentMetadata {

    private Long id;
    private String documentName;
    private LocalDateTime createdAt;
    private String createdBy;

    public DocumentMetadata() {
    }

    public DocumentMetadata(Long id, String documentName, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.documentName = documentName;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "DocumentMetadata{" +
                "id=" + id +
                ", documentName='" + documentName + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
