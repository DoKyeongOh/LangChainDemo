package com.odk.pjt.langchaindemo.chat.dto;

import java.util.List;

/**
 * 질의 분석 결과 DTO (Memory 계층 - 휘발성)
 */
public class QueryAnalysisResult {

    private String userId;
    private String intent;
    private String originalQuery;
    private List<String> keywords;

    public QueryAnalysisResult() {
    }

    public QueryAnalysisResult(String userId, String intent, String originalQuery, List<String> keywords) {
        this.userId = userId;
        this.intent = intent;
        this.originalQuery = originalQuery;
        this.keywords = keywords;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getOriginalQuery() {
        return originalQuery;
    }

    public void setOriginalQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "QueryAnalysisResult{" +
                "userId='" + userId + '\'' +
                ", intent='" + intent + '\'' +
                ", originalQuery='" + originalQuery + '\'' +
                ", keywords=" + keywords +
                '}';
    }
}
