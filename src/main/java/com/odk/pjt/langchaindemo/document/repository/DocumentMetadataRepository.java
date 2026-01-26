package com.odk.pjt.langchaindemo.document.repository;

import com.odk.pjt.langchaindemo.document.model.DocumentMetadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 문서 메타데이터 Repository (Spring JDBC 기반)
 */
@Repository
public class DocumentMetadataRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<DocumentMetadata> ROW_MAPPER = (rs, rowNum) -> {
        DocumentMetadata metadata = new DocumentMetadata();
        metadata.setId(rs.getLong("id"));
        metadata.setDocumentName(rs.getString("document_name"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            metadata.setCreatedAt(createdAt.toLocalDateTime());
        }

        metadata.setCreatedBy(rs.getString("created_by"));
        return metadata;
    };

    public DocumentMetadataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 문서 메타데이터 저장
     */
    public DocumentMetadata save(DocumentMetadata metadata) {
        if (metadata.getId() == null) {
            return insert(metadata);
        } else {
            return update(metadata);
        }
    }

    private DocumentMetadata insert(DocumentMetadata metadata) {
        String sql = "INSERT INTO document_metadata (document_name, created_at, created_by) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, metadata.getDocumentName());
            ps.setTimestamp(2, metadata.getCreatedAt() != null
                    ? Timestamp.valueOf(metadata.getCreatedAt())
                    : Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(3, metadata.getCreatedBy());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            metadata.setId(key.longValue());
        }

        return metadata;
    }

    private DocumentMetadata update(DocumentMetadata metadata) {
        String sql = "UPDATE document_metadata SET document_name = ?, created_at = ?, created_by = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                metadata.getDocumentName(),
                metadata.getCreatedAt() != null ? Timestamp.valueOf(metadata.getCreatedAt()) : null,
                metadata.getCreatedBy(),
                metadata.getId());

        return metadata;
    }

    /**
     * ID로 문서 메타데이터 조회
     */
    public Optional<DocumentMetadata> findById(Long id) {
        String sql = "SELECT id, document_name, created_at, created_by FROM document_metadata WHERE id = ?";

        List<DocumentMetadata> results = jdbcTemplate.query(sql, ROW_MAPPER, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    /**
     * 모든 문서 메타데이터 조회
     */
    public List<DocumentMetadata> findAll() {
        String sql = "SELECT id, document_name, created_at, created_by FROM document_metadata ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    /**
     * 문서 이름으로 조회
     */
    public List<DocumentMetadata> findByDocumentName(String documentName) {
        String sql = "SELECT id, document_name, created_at, created_by FROM document_metadata WHERE document_name = ?";
        return jdbcTemplate.query(sql, ROW_MAPPER, documentName);
    }

    /**
     * 문서 메타데이터 삭제
     */
    public void deleteById(Long id) {
        String sql = "DELETE FROM document_metadata WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 문서 존재 여부 확인
     */
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM document_metadata WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
