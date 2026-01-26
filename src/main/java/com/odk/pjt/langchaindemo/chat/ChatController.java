package com.odk.pjt.langchaindemo.chat;

import com.odk.pjt.langchaindemo.chat.service.ChatService;
import dev.langchain4j.data.message.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    /**
     * 단순 질문/응답 (히스토리 없음)
     */
    @PostMapping("/question")
    public ResponseEntity<Map<String, String>> sendQuestion(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        String answer = chatService.sendQuestion(question);
        return ResponseEntity.ok(Map.of("answer", answer));
    }

    /**
     * 세션 기반 대화 (히스토리 유지)
     */
    @PostMapping("/session/{sessionId}")
    public ResponseEntity<Map<String, String>> chat(
            @PathVariable String sessionId,
            @RequestBody Map<String, String> request) {
        String message = request.get("message");
        String response = chatService.chat(sessionId, message);
        return ResponseEntity.ok(Map.of("response", response));
    }

    /**
     * 시스템 프롬프트와 함께 새 대화 시작
     */
    @PostMapping("/session/{sessionId}/start")
    public ResponseEntity<Map<String, String>> startConversation(
            @PathVariable String sessionId,
            @RequestBody Map<String, String> request) {
        String systemPrompt = request.get("systemPrompt");
        String message = request.get("message");
        String response = chatService.chatWithSystemPrompt(sessionId, systemPrompt, message);
        return ResponseEntity.ok(Map.of("response", response));
    }

    /**
     * 대화 히스토리 조회
     */
    @GetMapping("/session/{sessionId}/history")
    public ResponseEntity<List<ChatMessage>> getHistory(@PathVariable String sessionId) {
        List<ChatMessage> history = chatService.getConversationHistory(sessionId);
        return ResponseEntity.ok(history);
    }

    /**
     * 대화 히스토리 초기화
     */
    @DeleteMapping("/session/{sessionId}")
    public ResponseEntity<Map<String, String>> clearConversation(@PathVariable String sessionId) {
        chatService.clearConversation(sessionId);
        return ResponseEntity.ok(Map.of("message", "대화가 초기화되었습니다."));
    }

    /**
     * 시스템 프롬프트 업데이트
     */
    @PutMapping("/session/{sessionId}/system-prompt")
    public ResponseEntity<Map<String, String>> updateSystemPrompt(
            @PathVariable String sessionId,
            @RequestBody Map<String, String> request) {
        String systemPrompt = request.get("systemPrompt");
        chatService.updateSystemPrompt(sessionId, systemPrompt);
        return ResponseEntity.ok(Map.of("message", "시스템 프롬프트가 업데이트되었습니다."));
    }

    /**
     * 활성 세션 수 조회
     */
    @GetMapping("/sessions/count")
    public ResponseEntity<Map<String, Integer>> getActiveSessionCount() {
        int count = chatService.getActiveSessionCount();
        return ResponseEntity.ok(Map.of("activeSessionCount", count));
    }
}
