package com.odk.pjt.langchaindemo.document.service;

import com.odk.pjt.langchaindemo.document.repository.DocumentMetadataRepository;
import com.odk.pjt.langchaindemo.embedding.DataEmbeddingPipeline;
import org.springframework.stereotype.Service;

/**
 * [P1.0] 문서 수집 및 인덱싱 서비스
 * 파일 업로드 → 텍스트 추출 → 조항 분할 → 메타데이터 추출 → RDB/Vector DB 적재
 */
@Service
public class DocumentIngestionService {

    private final DocumentMetadataRepository metadataRepository;
    private final DataEmbeddingPipeline embeddingPipeline;

    public DocumentIngestionService(DocumentMetadataRepository metadataRepository,
            DataEmbeddingPipeline embeddingPipeline) {
        this.metadataRepository = metadataRepository;
        this.embeddingPipeline = embeddingPipeline;
    }

    // TODO: 문서 인제스트 메서드 (파일 → 텍스트 추출 → 임베딩 → 저장)
    // TODO: LLM 메타데이터(키워드) 추출 메서드
}
