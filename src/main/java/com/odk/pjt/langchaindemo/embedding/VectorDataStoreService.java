package com.odk.pjt.langchaindemo.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;

import java.util.List;

public class VectorDataStoreService implements DataStoreService {

    private final EmbeddingStore<TextSegment> embeddingStore;

    public VectorDataStoreService(EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingStore = embeddingStore;
    }

    @Override
    public void store(Embedding embedding, TextSegment textSegment) {
        embeddingStore.add(embedding, textSegment);
    }

    @Override
    public void storeAll(List<Embedding> embeddings, List<TextSegment> textSegments) {
        embeddingStore.addAll(embeddings, textSegments);
    }
}
