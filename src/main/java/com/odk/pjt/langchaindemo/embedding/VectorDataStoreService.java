package com.odk.pjt.langchaindemo.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorDataStoreService implements DataStoreService {

    private final InMemoryEmbeddingStore<TextSegment> embeddingStore;

    public VectorDataStoreService() {
        this.embeddingStore = new InMemoryEmbeddingStore<>();
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
