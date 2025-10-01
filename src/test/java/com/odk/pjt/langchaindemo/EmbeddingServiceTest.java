package com.odk.pjt.langchaindemo;

import org.junit.jupiter.api.Test;

class EmbeddingServiceTest {

    private String apiKey = "";

    @Test
    public void test() {
        EmbeddingService embeddingService = new EmbeddingService(apiKey);
        float[] helloWorlds = embeddingService.embed("hello world");
    }

}