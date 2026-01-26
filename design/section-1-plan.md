# 🛠️ [P 1.0] 규정 문서 전처리 및 인덱싱 상세 계획서

이 프로세스는 관리자가 업로드한 비정형 규정 문서를 분석하여 검색과 검증에 최적화된 데이터로 변환하고, 각 저장소에 적절히 배분하는 기반 작업을 담당합니다.

---

## 1. 하위 데이터 흐름도 (Level 2 DFD)

(아래 머메이드 코드는 시각화 도구에서 프로세스 1.0의 상세 흐름을 확인하는 용도로 사용하세요)

graph TD
    Admin[관리자]
    
    P1_1(["1.1 원문 텍스트 추출"])
    P1_2(["1.2 조항 단위 텍스트 분할(Chunking)"])
    P1_3(["1.3 메타데이터 및 키워드 추출(LLM)"])
    P1_4(["1.4 벡터 변환 및 저장"])

    RDB[RDB: 규정 메타데이터]
    VDB[Vector DB: 임베딩 및 필터]

    Admin -->|규정 파일<br/>PDF/Word/Excel/TXT| P1_1
    P1_1 -->|구조화된 텍스트| P1_2
    P1_2 -->|문서 조각| P1_3
    P1_3 -->|id, 제목, 날짜| RDB
    P1_3 -->|id, 키워드, 조각내용| P1_4
    P1_4 -->|임베딩 데이터| VDB

---

## 2. 단계별 상세 구현 가이드

### **1.1 원문 텍스트 추출**
* **목표**: 규정 문서의 원문을 손상/손실 없이 정확하게 추출합니다.
* **지원 형식**: PDF, Word(.doc/.docx), Excel(.xlsx), TXT 4가지 형식을 우선 지원합니다.
* **구현 클래스**: `TextExtractionService` - LangChain4j의 `DocumentParser` 인터페이스를 활용하여 통합 처리
* **핵심 기술 (형식별 추출 방식)**:
  | 형식 | 구현 방식 | 상태 |
  | :--- | :--- | :--- |
  | PDF | `ApachePdfBoxDocumentParser` | ✅ 구현 완료 |
  | Word (.doc/.docx) | `ApachePoiDocumentParser` | ✅ 구현 완료 |
  | Excel (.xls/.xlsx) | `ApachePoiDocumentParser` | ✅ 구현 완료 |
  | TXT | Java `Files.readString()` | ✅ 구현 완료 |
* **작업 내용**: 파일 확장자를 기준으로 적절한 파서를 선택하여 원문을 추출합니다. 표나 특수 서식이 포함된 경우 별도 검증 로직을 통해 누락 여부를 확인합니다.

### **1.2 조항 단위 텍스트 분할 (Semantic Chunking)**
* **목표**: 검색 정확도를 높이기 위해 글자 수 기준이 아닌 '제n조' 단위로 문서를 분할합니다.
* **구현 로직**: 정규 표현식(`^제\s?\d+\s?조`)을 사용하여 각 조항을 독립적인 섹션으로 구분합니다.
* **맥락 유지**: 분할 시 해당 조항이 속한 상위 제목을 접두사로 추가하여 정보의 유실을 방지합니다.

### **1.3 메타데이터 및 키워드 추출 (LLM Preprocessing)**
* **목표**: 분할된 각 조각에서 검색 필터로 사용할 속성과 키워드를 추출합니다.
* **추출 항목**: 조항 번호, 핵심 키워드 리스트, 적용 대상, 시행일/만료일 정보를 포함합니다.
* **데이터 흐름**: 추출된 키워드는 RDB에 별도 저장하지 않고 Vector DB의 메타데이터 필드로 통합 관리합니다.

### **1.4 벡터 변환 및 저장**
* **목표**: 텍스트를 임베딩 벡터로 변환하여 검색 엔진에 적재합니다.
* **RDB 저장**: `id`, `문서이름`, `등록일` 등 관리용 마스터 정보를 저장합니다.
* **Vector DB 저장**: 임베딩 값과 함께 `rdb_id`, `키워드(list)`, `조항번호` 등을 메타데이터로 함께 저장하여 필터링 성능을 확보합니다.

---

## 3. 데이터 매핑 테이블 (Storage Mapping)

| 구분 | 저장소 | 필드(Field) 구성 | 비고 |
| :--- | :--- | :--- | :--- |
| **관리 정보** | **RDB** | `id`, `문서이름`, `등록일`, `등록유저ID` | 마스터 데이터 및 검증용 |
| **검색 정보** | **Vector DB** | `vector`, `text`, `keywords(list)`, `rdb_id`, `article_no` | 검색 및 Pre-filtering용 |

---

## 4. 커서(Cursor) IDE 구현 체크리스트

* [ ] 규정 파일(PDF/Word/Excel/TXT)을 업로드받아 서버 폴더에 저장하는 API 개발
* [x] `TextExtractionService` 구현 (LangChain4j DocumentParser 활용)
  * [x] PDF - `ApachePdfBoxDocumentParser`
  * [x] Word (.doc/.docx) - `ApachePoiDocumentParser`
  * [x] Excel (.xls/.xlsx) - `ApachePoiDocumentParser`
  * [x] TXT - Java Files API
* [ ] 추출된 텍스트의 무결성 검증 테스트
* [ ] Regex 기반의 `ArticleSplitter` 클래스 작성
* [ ] LangChain4j `AiServices`를 활용한 조항별 메타데이터 추출 프롬프트 설계
* [ ] JPA를 통한 RDB 저장과 `EmbeddingStore`를 통한 벡터 저장 로직 연동

---


1번 프로세스의 설계 및 계획이 완료되었습니다. 이 계획서를 바탕으로 실제 코딩을 시작하시면 됩니다.

**다음으로 [P 2.0] 사용자 질의 분석 프로세스에 대한 계획서를 만들어 드릴까요?**