package com.odk.pjt.langchaindemo.search;

import com.odk.pjt.langchaindemo.chat.dto.QueryAnalysisResult;
import com.odk.pjt.langchaindemo.document.repository.DocumentMetadataRepository;
import com.odk.pjt.langchaindemo.search.dto.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * [P3.0] 관련 정보 검색 및 필터링 서비스
 * 메타데이터 Pre-filtering -> 유사도 검색 -> RDB 교차 검증
 */
@Service
public class SearchService {

    private final DocumentMetadataRepository metadataRepository;
    // TODO: VectorStore 주입

    public SearchService(DocumentMetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    // TODO: 메타데이터 기반 Pre-filtering
    // TODO: 유사도 검색 (오버샘플링)
    // TODO: RDB 교차 검증 및 상세 정보 결합
    public List<SearchResult> search(QueryAnalysisResult analysisResult) {
        return List.of();
    }
}
