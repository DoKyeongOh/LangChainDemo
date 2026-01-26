package com.odk.pjt.langchaindemo.chat.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    private static final int DEFAULT_MAX_MESSAGES = 20;
    private static final String DEFAULT_SYSTEM_PROMPT = "당신은 친절하고 도움이 되는 AI 어시스턴트입니다.";

    // 사용자별 ChatMemory 저장소
    private final Map<String, ChatMemory> userMemories = new ConcurrentHashMap<>();

    // 사용자별 시스템 프롬프트 저장소
    private final Map<String, String> userSystemPrompts = new ConcurrentHashMap<>();

    @Autowired
    private ChatModel chatModel;

    public ChatService() {

    }

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * 단순 질문/응답 (히스토리 없음)
     */
    public String sendQuestion(String question) {
        return chatModel.chat(question);
    }

    /**
     * 대화 히스토리를 유지하는 챗 메서드
     * 
     * @param sessionId   세션/사용자 식별자
     * @param userMessage 사용자 메시지
     * @return AI 응답
     */
    public String chat(String sessionId, String userMessage) {
        ChatMemory memory = getOrCreateMemory(sessionId);
        String systemPrompt = userSystemPrompts.getOrDefault(sessionId, DEFAULT_SYSTEM_PROMPT);

        // 시스템 메시지 추가 (첫 메시지인 경우)
        if (memory.messages().isEmpty()) {
            memory.add(SystemMessage.from(systemPrompt));
        }

        // 사용자 메시지 추가
        memory.add(UserMessage.from(userMessage));

        // AI 응답 생성
        ChatResponse response = chatModel.chat(memory.messages());
        AiMessage aiMessage = response.aiMessage();

        // AI 응답을 메모리에 추가
        memory.add(aiMessage);

        return aiMessage.text();
    }

    /**
     * 시스템 프롬프트를 설정하고 대화 시작
     * 
     * @param sessionId    세션/사용자 식별자
     * @param systemPrompt 시스템 프롬프트
     * @param userMessage  사용자 메시지
     * @return AI 응답
     */
    public String chatWithSystemPrompt(String sessionId, String systemPrompt, String userMessage) {
        // 새로운 대화 시작 시 기존 메모리 초기화
        clearConversation(sessionId);
        userSystemPrompts.put(sessionId, systemPrompt);
        return chat(sessionId, userMessage);
    }

    /**
     * 대화 히스토리 조회
     * 
     * @param sessionId 세션/사용자 식별자
     * @return 대화 메시지 목록
     */
    public List<ChatMessage> getConversationHistory(String sessionId) {
        ChatMemory memory = userMemories.get(sessionId);
        return memory != null ? memory.messages() : List.of();
    }

    /**
     * 대화 히스토리 초기화
     * 
     * @param sessionId 세션/사용자 식별자
     */
    public void clearConversation(String sessionId) {
        userMemories.remove(sessionId);
        userSystemPrompts.remove(sessionId);
    }

    /**
     * 시스템 프롬프트 변경 (대화 유지)
     * 
     * @param sessionId       세션/사용자 식별자
     * @param newSystemPrompt 새 시스템 프롬프트
     */
    public void updateSystemPrompt(String sessionId, String newSystemPrompt) {
        userSystemPrompts.put(sessionId, newSystemPrompt);

        ChatMemory memory = userMemories.get(sessionId);
        if (memory != null && !memory.messages().isEmpty()) {
            // 첫 번째 메시지가 시스템 메시지인 경우 교체
            List<ChatMessage> messages = memory.messages();
            if (!messages.isEmpty() && messages.get(0) instanceof SystemMessage) {
                messages.set(0, SystemMessage.from(newSystemPrompt));
            }
        }
    }

    /**
     * 활성 세션 수 조회
     * 
     * @return 활성 세션 수
     */
    public int getActiveSessionCount() {
        return userMemories.size();
    }

    /**
     * 특정 세션의 메모리 가져오기 또는 생성
     */
    private ChatMemory getOrCreateMemory(String sessionId) {
        return userMemories.computeIfAbsent(sessionId,
                id -> MessageWindowChatMemory.withMaxMessages(DEFAULT_MAX_MESSAGES));
    }
}
