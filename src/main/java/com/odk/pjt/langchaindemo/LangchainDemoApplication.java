package com.odk.pjt.langchaindemo;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;

@SpringBootApplication
@EnableScheduling
public class LangchainDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LangchainDemoApplication.class, args);
    }

    @Bean
    public EmbeddingModel embeddingModel(@Value("${google.ai.gemini.api-key}") String apiKey) {
        return GoogleAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName("text-embedding-004") // 가성비가 좋은 최신 임베딩 모델
                .build();
    }

    @Bean
    public ChatModel chatModel(@Value("${google.ai.gemini.api-key}") String apiKey) {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .build();
    }

}
