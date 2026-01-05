package com.odk.pjt.langchaindemo.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;

import java.util.List;
import java.util.stream.Collectors;

public class PlainTextEmbeddingService implements DataEmbeddingService {

    private final EmbeddingModel embeddingModel;

    public PlainTextEmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Override
    public List<Embedding> embedAll(List<TextSegment> textSegments) {
        Response<List<Embedding>> response = embeddingModel.embedAll(textSegments);
        return response.content();
    }

    @Override
    public Embedding embed(TextSegment textSegment) {
        Response<Embedding> response = embeddingModel.embed(textSegment);
        return response.content();
    }

    /**
     * 평문 텍스트 리스트를 TextSegment로 변환하여 임베딩합니다.
     */
    public List<Embedding> embedTexts(List<String> texts) {
        List<TextSegment> segments = texts.stream()
                .map(TextSegment::from)
                .collect(Collectors.toList());
        return embedAll(segments);
    }
}
