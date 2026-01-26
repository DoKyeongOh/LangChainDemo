package com.odk.pjt.langchaindemo.chat.service;

import com.odk.pjt.langchaindemo.chat.dto.QueryAnalysisResult;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * [P2.0] 사용자 질의 분석 AI Service 인터페이스
 * LangChain4j AiServices를 통해 자동으로 구현체가 생성됩니다.
 */
public interface QueryAnalyzer {

    @SystemMessage("""
            당신은 규정 검색 시스템의 질의 분석기입니다.
            사용자의 자연어 질문을 분석하여 다음을 추출합니다:
            1. intent: 사용자의 의도 (예: SEARCH_REGULATION, ASK_PROCEDURE, CHECK_ELIGIBILITY 등)
            2. keywords: 검색에 사용할 핵심 키워드 리스트

            응답은 반드시 지정된 JSON 형식으로 반환하세요.
            """)
    @UserMessage("""
            사용자 ID: {{userId}}
            질문: {{query}}

            위 질문을 분석하여 의도와 키워드를 추출해주세요.
            """)
    QueryAnalysisResult analyze(@V("userId") String userId, @V("query") String query);
}
