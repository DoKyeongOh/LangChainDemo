package com.odk.pjt.langchaindemo.notion;

import notion.api.v1.NotionClient;
import notion.api.v1.model.blocks.*;
import notion.api.v1.model.search.SearchResult;
import notion.api.v1.model.search.SearchResults;
import notion.api.v1.request.search.SearchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotionCollectClient {

    private final NotionClient client;

    public NotionCollectClient(@Value("${notion.api-token}") String apiToken) {
        this.client = new NotionClient(apiToken);
    }

    public List<SearchResult> discover() {
        // Search API 호출
        SearchResults results = client.search(new SearchRequest(
                "", // query: 빈 값일 경우 전체 검색
                null, // sort: 정렬 기준 (필요시 설정)
                null, // filter: "page" 또는 "database"로 필터링 가능
                null, // startCursor: 페이징 처리를 위한 커서
                100 // pageSize: 한 번에 가져올 최대 개수 (최대 100)
        ));
        return results.getResults();
    }

    /**
     * 페이지 ID를 입력받아 페이지의 하위 블록 리스트를 반환합니다.
     *
     * @param pageId Notion 페이지 ID
     * @return 블록 리스트
     */
    public List<Block> retrieveBlockChildren(String pageId) {
        return client.retrieveBlockChildren(pageId, null, 100).getResults();
    }

}
