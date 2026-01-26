package com.odk.pjt.langchaindemo.embedding;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataEmbeddingPipeline {

    Logger logger = LoggerFactory.getLogger(DataEmbeddingPipeline.class);

    private final EmbeddingModel embeddingModel;
    private final VectorDataStoreService storeService;

    public DataEmbeddingPipeline(EmbeddingModel embeddingModel, VectorDataStoreService storeService) {
        this.embeddingModel = embeddingModel;
        this.storeService = storeService;
    }

    public void embed(String text, Map<String, Object> metadata) {
        Metadata meta = Metadata.from(metadata);
        TextSegment segment = TextSegment.from(text, meta);
        Embedding embedding = embeddingModel.embed(segment).content();
        storeService.store(embedding, segment);

        logger.info("단 건 임베딩 및 저장 완료");
    }

    public void embedAll(List<String> texts, Map<String, Object> metadata) {
        Metadata meta = Metadata.from(metadata);
        List<TextSegment> segments = texts.stream().map(text -> TextSegment.from(text, meta)).toList();

        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        storeService.storeAll(embeddings, segments);

        logger.info("임베딩 및 저장 완료: " + segments.size() + "건");
    }
}
