package com.odk.pjt.langchaindemo.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import java.util.List;

public interface DataEmbeddingService {
    List<Embedding> embedAll(List<TextSegment> textSegments);

    Embedding embed(TextSegment textSegment);
}
