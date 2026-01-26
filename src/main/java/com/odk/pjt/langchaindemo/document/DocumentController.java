package com.odk.pjt.langchaindemo.document;

import com.odk.pjt.langchaindemo.document.service.DocumentIngestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [P1.0] 문서 업로드 및 관리 API 컨트롤러
 */
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentIngestionService ingestionService;

    public DocumentController(DocumentIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    // TODO: 파일 업로드 엔드포인트
    // TODO: 문서 목록 조회 엔드포인트
    // TODO: 문서 삭제 엔드포인트
}
