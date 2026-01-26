package com.odk.pjt.langchaindemo.config;

import com.odk.pjt.langchaindemo.chat.service.QueryAnalyzer;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LangChain4j AI Service 빈 설정
 */
@Configuration
public class AiServiceConfig {

    @Bean
    public QueryAnalyzer queryAnalyzer(ChatModel chatModel) {
        return AiServices.builder(QueryAnalyzer.class)
                .chatModel(chatModel)
                .build();
    }
}
