package com.odk.pjt.langchaindemo.notion;

import notion.api.v1.model.blocks.Block;
import notion.api.v1.model.blocks.BulletedListItemBlock;
import notion.api.v1.model.blocks.CalloutBlock;
import notion.api.v1.model.blocks.HeadingOneBlock;
import notion.api.v1.model.blocks.HeadingThreeBlock;
import notion.api.v1.model.blocks.HeadingTwoBlock;
import notion.api.v1.model.blocks.NumberedListItemBlock;
import notion.api.v1.model.blocks.ParagraphBlock;
import notion.api.v1.model.blocks.QuoteBlock;
import notion.api.v1.model.blocks.ToDoBlock;
import notion.api.v1.model.blocks.ToggleBlock;
import notion.api.v1.model.common.PropertyType;
import notion.api.v1.model.pages.PageProperty;
import notion.api.v1.model.search.SearchResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotionDataParser {

    /**
     * 페이지 정보와 블록들을 합쳐 하나의 문자열로 변환합니다.
     */
    public String parsePage(SearchResult page, List<Block> blocks) {
        StringBuilder sb = new StringBuilder();

        // 제목 추출
        Map<String, PageProperty> properties = page.asPage().getProperties();
        String title = properties.values().stream()
                .filter(prop -> prop.getType() == PropertyType.Title)
                .findFirst()
                .map(prop -> prop.getTitle().stream()
                        .map(PageProperty.RichText::getPlainText)
                        .collect(Collectors.joining()))
                .orElse("Untitled");

        sb.append("Title: ").append(title).append("\n\n");

        // 본문 블록 추출
        for (Block block : blocks) {
            String text = extractTextFromBlock(block);
            if (text != null && !text.isEmpty()) {
                sb.append(text).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * 데이터베이스 정보를 문자열로 변환합니다.
     */
    public String parseDatabase(SearchResult database) {
        String title = database.asDatabase().getTitle().stream()
                .map(it -> it.getPlainText())
                .reduce("", String::concat);

        return "Database Title: " + title;
    }

    private String extractTextFromBlock(Block block) {
        List<PageProperty.RichText> richText = null;

        if (block instanceof ParagraphBlock) {
            richText = ((ParagraphBlock) block).getParagraph().getRichText();
        } else if (block instanceof HeadingOneBlock) {
            richText = ((HeadingOneBlock) block).getHeading1().getRichText();
        } else if (block instanceof HeadingTwoBlock) {
            richText = ((HeadingTwoBlock) block).getHeading2().getRichText();
        } else if (block instanceof HeadingThreeBlock) {
            richText = ((HeadingThreeBlock) block).getHeading3().getRichText();
        } else if (block instanceof BulletedListItemBlock) {
            richText = ((BulletedListItemBlock) block).getBulletedListItem().getRichText();
        } else if (block instanceof NumberedListItemBlock) {
            richText = ((NumberedListItemBlock) block).getNumberedListItem().getRichText();
        } else if (block instanceof ToDoBlock) {
            richText = ((ToDoBlock) block).getToDo().getRichText();
        } else if (block instanceof ToggleBlock) {
            richText = ((ToggleBlock) block).getToggle().getRichText();
        } else if (block instanceof QuoteBlock) {
            richText = ((QuoteBlock) block).getQuote().getRichText();
        } else if (block instanceof CalloutBlock) {
            richText = ((CalloutBlock) block).getCallout().getRichText();
        }

        if (richText != null) {
            return richText.stream()
                    .map(PageProperty.RichText::getPlainText)
                    .collect(Collectors.joining());
        }
        return null;
    }
}
