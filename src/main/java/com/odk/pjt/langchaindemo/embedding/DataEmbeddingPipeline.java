package com.odk.pjt.langchaindemo.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataEmbeddingPipeline {

    private final PlainTextEmbeddingService embeddingService;
    private final VectorDataStoreService storeService;

    public DataEmbeddingPipeline(PlainTextEmbeddingService embeddingService, VectorDataStoreService storeService) {
        this.embeddingService = embeddingService;
        this.storeService = storeService;
    }

    /**
     * 평문 텍스트 리스트를 입력받아 임베딩하고 저장소에 저장하는 파이프라인을 실행합니다.
     */
    public void run(List<String> texts) {
        // 1. Plain Text -> TextSegment 변환
        List<TextSegment> segments = texts.stream()
                .map(TextSegment::from)
                .collect(Collectors.toList());

        processSegments(segments);
    }

    /**
     * 단일 텍스트와 메타데이터를 입력받아 처리합니다.
     */
    public void run(String text, Map<String, Object> metadata) {
        TextSegment segment = TextSegment.from(text, Metadata.from(metadata));
        processSegments(List.of(segment));
    }

    private void processSegments(List<TextSegment> segments) {
        if (segments.isEmpty())
            return;

        // 2. TextSegment -> Embedding 생성
        List<Embedding> embeddings = embeddingService.embedAll(segments);

        // 3. Embedding & TextSegment 저장
        storeService.storeAll(embeddings, segments);

        System.out.println("임베딩 및 저장 완료: " + segments.size() + "건");
    }
}
