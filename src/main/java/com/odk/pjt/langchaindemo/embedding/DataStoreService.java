package com.odk.pjt.langchaindemo.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import java.util.List;

public interface DataStoreService {
    void store(Embedding embedding, TextSegment textSegment);

    void storeAll(List<Embedding> embeddings, List<TextSegment> textSegments);
}
