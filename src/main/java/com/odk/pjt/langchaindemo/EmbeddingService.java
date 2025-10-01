package com.odk.pjt.langchaindemo;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.spi.model.embedding.EmbeddingModelFactory;

public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    // 기본 생성자 (OpenAI 모델 사용)
    public EmbeddingService(String apiKey) {
        EmbeddingModelFactory embeddingModelFactory = null;
        this.embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName("text-embedding-3-small")
                .build();
    }

    // 텍스트 하나 임베딩
    public float[] embed(String text) {
        Embedding embedding = embeddingModel.embed(text).content();
        return embedding.vector();
    }
}
