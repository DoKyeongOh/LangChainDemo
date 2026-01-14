package com.odk.pjt.langchaindemo.notion;

import com.odk.pjt.langchaindemo.embedding.DataEmbeddingPipeline;
import notion.api.v1.model.blocks.Block;
import notion.api.v1.model.common.ObjectType;
import notion.api.v1.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotionIngestionService {

    private final NotionCollectClient client;
    private final NotionDataParser parser;
    private final DataEmbeddingPipeline pipeline;

    public NotionIngestionService(NotionCollectClient client, NotionDataParser parser, DataEmbeddingPipeline pipeline) {
        this.client = client;
        this.parser = parser;
        this.pipeline = pipeline;
    }

    /**
     * 노션 데이터를 수집, 파싱하여 개별적으로 임베딩 파이프라인에 전달합니다.
     */
    public void ingest() {
        List<SearchResult> results = client.discover();

        for (SearchResult result : results) {
            String text = "";
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("id", result.getId());
            metadata.put("type", result.getObjectType().name());

            if (result.getObjectType() == ObjectType.Page) {
                // 페이지의 경우 상세 블록 데이터를 가져와서 파싱
                List<Block> blocks = client.retrieveBlockChildren(result.getId());
                text = parser.parsePage(result, blocks);
            } else if (result.getObjectType() == ObjectType.Database) {
                // 데이터베이스의 경우 기본 정보 파싱
                text = parser.parseDatabase(result);
            }

            if (!text.isEmpty()) {
                // 개별 오브젝트 단위로 임베딩 파이프라인 실행
                pipeline.run(text, metadata);
            }
        }
    }
}
