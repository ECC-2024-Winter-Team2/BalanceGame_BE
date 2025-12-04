

# ⚖️ Balance Game Backend (밸런스 게임)

**ECC 2024 Winter Team 2** 프로젝트의 백엔드 리포지토리입니다.
사용자가 다양한 카테고리의 밸런스 게임(양자택일) 질문에 투표하고, 결과를 확인하며 댓글을 통해 소통할 수 있는 서비스입니다.

## 📝 프로젝트 소개

이 프로젝트는 사용자가 두 가지 선택지 중 하나를 고르는 '밸런스 게임'을 제공합니다.
사용자의 투표 결과를 저장하고, 전체 통계(퍼센트)를 계산하여 보여주며, 다수의 선택과 얼마나 일치하는지('근본' 여부)를 분석해줍니다. 또한 익명 댓글 기능을 통해 사용자 간의 소통을 지원합니다.

## 🛠 Tech Stack

| 분류 | 기술 |
| --- | --- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.4.2 |
| **Build Tool** | Gradle |
| **Database** | MySQL |
| **ORM** | JPA (Hibernate) |
| **API Test** | Postman / Swagger (Optional) |

## 📂 프로젝트 구조 (Package Structure)

```
src/main/java/com/ecc/balancegame
├── config          # 설정 클래스 (WebConfig 등)
├── controller      # API 엔드포인트 처리
├── domain          # Entity 클래스 (DB 테이블 매핑)
├── dto             # 데이터 전송 객체 (Request/Response)
├── exception       # 전역 예외 처리
├── repository      # DB 접근 계층 (JPA Repository)
└── service         # 비즈니스 로직 처리
```

## ✨ 주요 기능

1.  **유저 관리**
      * 닉네임 중복 확인 및 등록
2.  **게임 플레이 (투표)**
      * 카테고리별 질문 조회
      * 선택지 투표 (DB 저장)
3.  **결과 및 통계**
      * 사용자의 선택과 대중의 선택 일치도 분석 ('근본' 판별)
      * 특정 카테고리/질문별 선택 비율(%) 조회
4.  **커뮤니티 (댓글)**
      * 질문별 댓글 작성 (익명, 비밀번호 기반)
      * 댓글 수정 및 삭제 (비밀번호 검증)
      * 댓글 좋아요 및 좋아요 취소

## 📡 API 명세 (API Endpoints)

### 👤 User (유저)

| Method | URI | Description |
| :--- | :--- | :--- |
| `POST` | `/api/user/username` | 닉네임 등록 및 유저 생성 |

### 📂 Category & Question (카테고리 및 질문)

| Method | URI | Description |
| :--- | :--- | :--- |
| `GET` | `/api/categories` | 전체 카테고리 목록 조회 |
| `GET` | `/api/categories/{categoryId}/questions` | 특정 카테고리의 질문 및 선택지 목록 조회 |

### ✅ Choice & Vote (투표)

| Method | URI | Description |
| :--- | :--- | :--- |
| `POST` | `/api/choices` | 사용자의 게임 선택 결과 저장 (일괄 전송) |

### 📊 Game Result (결과)

| Method | URI | Description |
| :--- | :--- | :--- |
| `GET` | `/api/game/{userId}` | 사용자의 '근본' 일치도 결과 조회 |
| `GET` | `/api/game/result/{categoryId}` | 카테고리 내 질문별 선택 비율 조회 |
| `GET` | `/api/game/results` | 전체 질문에 대한 통계 조회 |
| `GET` | `/api/game/result?categoryName={name}` | 카테고리 이름으로 통계 조회 |

### 💬 Comment (댓글)

| Method | URI | Description |
| :--- | :--- | :--- |
| `GET` | `/api/comments` | 특정 질문의 댓글 목록 조회 (`categoryId`, `questionId` param 필요) |
| `POST` | `/api/comments` | 댓글 작성 |
| `PATCH` | `/api/comments/{commentId}` | 댓글 수정 (비밀번호 확인) |
| `DELETE` | `/api/comments/{commentId}` | 댓글 삭제 (비밀번호 확인) |
| `POST` | `/api/comments/{commentId}/like` | 댓글 좋아요 |
| `DELETE` | `/api/comments/{commentId}/like` | 댓글 좋아요 취소 |

## 🚀 설치 및 실행 방법 (Getting Started)

### 1\. Prerequisites

  * Java 17 이상
  * MySQL Database

### 2\. Repository Clone

```bash
git clone https://github.com/ecc-2024-winter-team2/balancegame_be.git
cd balancegame_be
```

### 3\. 환경 변수 설정 (Environment Variables)

`application.properties`에 정의된 DB 연결 정보를 환경 변수로 설정하거나 `application.properties` 파일을 직접 수정해야 합니다.

  * `DB_URL`: JDBC URL (예: `jdbc:mysql://localhost:3306/balancegame`)
  * `DB_USERNAME`: DB 사용자 이름
  * `DB_PASSWORD`: DB 비밀번호
  * `DB_DRIVER`: `com.mysql.cj.jdbc.Driver`

### 4\. Build & Run

**Mac/Linux:**

```bash
./gradlew clean build
./gradlew bootRun
```

**Windows:**

```cmd
gradlew.bat clean build
gradlew.bat bootRun
```

서버가 성공적으로 실행되면 `http://localhost:8080` 에서 접근 가능합니다.

-----

### ⚠️ Error Handling

  * **400 Bad Request**: 잘못된 입력값 (예: 빈 닉네임, 중복 닉네임)
  * **500 Internal Server Error**: 서버 내부 오류 (예: DB 연결 실패, 비밀번호 불일치)
  * 모든 에러 응답은 `ResponseDto` 형식(`status`, `message`)으로 반환됩니다.
