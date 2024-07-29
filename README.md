# 그림일기 (ImageDiary)

## 팀 및 프로젝트 소개
**ImageDiary**는 사진을 등록하고 일기를 작성할 수 있는 다이어리 앱입니다. 또한, D-day와 기념일을 추가할 수 있는 캘린더 기능도 제공하여 개인적인 중요한 날들을 관리할 수 있습니다.

## 프로젝트 기간
2024년 06월 10일 ~ 2024년 07월 04일

## 주요 기능
- **사진 등록 및 일기 작성**: 사용자가 일기와 함께 사진을 등록할 수 있습니다.
- **캘린더 기능**: D-day와 기념일을 추가 및 관리할 수 있습니다.

## 개발 환경
- Java 17
- IDE : Eclipse 2024-06
- Framework : Springboot(3.3.2)
- Database: Oracle DB
- ORM: Mybatis

## Requirements
- ** ImageDiary는 Java 17 이상의 버전을 요구합니다.
- 

## 설치 및 사용법
1. 레포지토리를 클론합니다:
    ```sh
    git clone https://github.com/kulee728/CodingCoggies.git
    ```
2. 프로젝트 디렉토리로 이동합니다:
    ```sh
    cd CodingCoggies
    ```
3. src/main/resources/application.properties 에 configuration을 작성합니다:
    ```sh
    server.port=[server포트번호]
    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
    spring.datasource.username=[DB계정명]
    spring.datasource.password=[DB계정비밀번호]
    
    mybatis.mapper-locations=classpath:/templates/mappers/*.xml
    
    logging.level.com.zaxxer.hikari=DEBUG
    logging.level.org.mybatis.spring=DEBUG
    mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
    
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=[인증발신용이메일(*gmail)]
    spring.mail.password=[인증발신용이메일비밀번호]
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.timeout=5000
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```
4. 프로젝트를 IDE 에서 불러온 뒤 Run as - Spring Boot Application 을 수행 합니다.:
5. 브라우저 주소창에 "http://localhost:[포트번호]" 를 입력합니다.
6. 
## 기여 방법
1. 이 레포지토리를 포크합니다.
2. 새로운 브랜치를 만듭니다:
    ```sh
    git checkout -b feature-branch
    ```
3. 기능을 추가하거나 버그를 수정합니다.
4. 변경 사항을 커밋합니다:
    ```sh
    git commit -m 'Add some feature'
    ```
5. 브랜치에 푸시합니다:
    ```sh
    git push origin feature-branch
    ```
6. Pull Request를 생성합니다.

## 라이선스
이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 `LICENSE` 파일을 참조하세요.

## 프로젝트 관련 URL
- [GitHub Repository](https://github.com/kulee728/CodingCoggies)

---

이 프로젝트에 대한 자세한 내용은 [Notion 페이지](https://gossamer-coat-559.notion.site/coddingCoggies-1694d46b6cf4441bb9ef97e49f71879d?pvs=25)에서 확인할 수 있습니다.
