package com.odk.pjt.langchaindemo.search.dto;

import java.util.List;

/**
 * [P3.0] 검색 결과 DTO
 */
public class SearchResult {

    private Long documentId;
    private String documentName;
    private String chunkText;
    private List<String> keywords;
    private double similarityScore;

    public SearchResult() {
    }

    public SearchResult(Long documentId, String documentName, String chunkText,
            List<String> keywords, double similarityScore) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.chunkText = chunkText;
        this.keywords = keywords;
        this.similarityScore = similarityScore;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getChunkText() {
        return chunkText;
    }

    public void setChunkText(String chunkText) {
        this.chunkText = chunkText;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                ", chunkText='" + chunkText + '\'' +
                ", keywords=" + keywords +
                ", similarityScore=" + similarityScore +
                '}';
    }
}
