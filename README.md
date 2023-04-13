## 💡 기술스택

### **✨Frontend**

- Android Studio(Kotlin)
- Retrofit

### **✨Backend**

- MySQL, QueryDSL
- Docker, Github action
- AWS RDS/EC2/S3
- SpringBoot, Spring data JPA, Spring Security, Redis

## 💡 **기술 스택 선정 이유**

### 🎁**Frontend**

- 트립지니는 여행 시 사용하는 애플리케이션으로 모바일 환경에 적합한 서비스 입니다.
- Android는 대표적인 오픈소스 플랫폼이며 최다 사용자를 보유한 모바일 운영체제입니다.
- Android Studio는 안드로이드 애플리케이션 개발을 위한 통합개발환경으로 유연한 Gradle 기반 빌드 시스템을 가지며, 모든 Android 기기용으로 개발할 수 있는 환경을 제공합니다.
- Kotlin은 높은 표현력과 간결함, Null Safe 제공을 특징으로 합니다. 또한 코루틴을 사용해 메모리를 효율적으로 사용하는 동시에 손쉽게 비동기를 처리할 수 있으며 Jetpack Compose를 사용해 UI 개발을 간소화, 가속화할 수 있습니다.

### 🎁**Backend**

- Spring boot + JAVA: 활용할 수 있는 래퍼런스가 많으며 범용성이 좋고, 의존성 주입을 통해 새로운 요구사항과 기능들에 대하여 높은 확장성을 가집니다.
- JPA: Spring boot를 사용하면서 DB를 관리하기 위한 대표적인 API입니다. JPA를 통해 간편하게 객체와 관계형 데이터베이스를 매핑시키며 CRUD 기능을 간단하게 처리할 수 있어 개발 생산성을 향상시킵니다.
- AWS EC2/RDS: 프리티어 계정을 통해 과금 없이 AWS EC2 인스턴스를 사용할 수 있기에 AWS를 사용하여 서버 배포를 진행합니다.
- Docker + Github Action:  Docker로 동일한 백엔드 환경을 구축할 수 있고, Github Action은 별도의 설치 없이 GitHub 리포지토리에서 CI/CD가 가능하다는 특징이 있어 선정했습니다.

## 💡 시스템 아키텍처

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cc3b161f-d438-4d14-9455-f9f65a57c200/Untitled.png)

## 💡 네이밍룰

- 파일 : PascalCase + CamelCase (ex.DeclarationPrecessor.kt)
- 패키지명: LowerCase (ex.service)
- 클래스명 : PascalCase + CamelCase (ex.SendMessage)
- 함수/변수명 : CamelCase (ex.userCount)
- 상수명 : UpperCase + SnakeCase (ex.MAX_COUNT)

## 💡 커밋컨벤션

> **Commit Message : [Type][Subject][Body][Footer]**
> 

### 📌 Type

- [Feat] : 새로운 기능 추가
- [Fix] : 버그 수정
- [Build] : 빌드 관련 파일 수정
- [Ci] : CI 관련 설정 수정
- [Docs] : 문서 수정
- [Style] : 코드 포맷팅, 세미콜론 누락, 로직 변경이 없는 경우
- [Refactor] : 코드 및 파일 리펙토링
- [Test] : 테스트 코드, 리펙토링 테스트 코드 추가
- [Chore] : 기타 변경사항

### 📌 Subject

- Type과 함께 작성, Body와 한 줄 띄워 분리
- 50자 이내, 첫 글자 대문자, 명령문 사용, 개조식 구문

### 📌 Body

- 선택사항
- 72자 이내, '어떻게'보다 '무엇을','왜' 에 대해 작성

### 📌 Footer

- 선택사항
- 이슈를 추적하기 위한 ID를 추가할 시 사용
- 해결/관련/참고 ID

## 💡 프레임워크 스터디 진행

- Frontend
    - Retrofit 라이브러리 스터디 진행
    - Jetpack Compose 스터디 진행
- Backend
    - Docker + Github Action을 활용한 CI/CD 환경 구축 스터디 진행
    - OAuth2.0 + JWT를 이용한 로그인/회원가입 스터디 진행
