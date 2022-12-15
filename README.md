
<h1 align="center">
	<img
		width="200"
		alt="The Lounge"
		src="https://user-images.githubusercontent.com/89582664/203536447-cd6a4ef4-75eb-4156-940b-ee12fe9aa5e1.png">
</h1>

<h3 align="center">
	(BSM 전용) 학습 증진 도움 앱 📖
</h3>

<p align="center">
	<strong>
		<a href="https://www.figma.com/file/CTo92mYXylGBeaXXX7xkef/%EC%9A%A9%EC%A0%9C-%26-%EA%B2%BD%EC%8B%A0?node-id=0%3A1&t=tMn1UqMhsPA6Nj0X-0">Design</a>
		


<p align="center">
	<img src="https://user-images.githubusercontent.com/89582664/203536228-2e98beba-ad17-4b33-96d5-5a458be86b64.png" width="550">
</p>

## 팀원역할

- **Mobile-FrontEnd** 조용제(Lovingcats)	
- **BackEnd** 이경신(Ajrdn)
<br>
		
## 앱 설명

<br>
		
### 앱을 만들게 된 계기
가끔씩 코딩을 하다가 중간에 휴대폰을 계속 봐서 집중력이 흐트러지는 경우가
많았는데 교내 랭킹으로 승부욕을 늘리고 휴대폰 사용을 못하게 하면 어떨까 라고 생각하게 되어 해당 앱을 생각하게 되었습니다

<br>
		
### 개발하고자 하는 목표
교내 학생들 중 휴대폰 때문에 오랫동안 집중하지 못하는 학생들의 학업을 증진시켜주는 앱을 만드는 것 입니다

<br>
		
### 사용기술스택
앱 :  [Flutter](https://flutter.dev/?gclid=Cj0KCQiAg_KbBhDLARIsANx7wAz5lYyBO9RFwhX-V1IJ_xWVuCK1cZkySEkWeqZMPGofPCvRPaHPlWAaAijFEALw_wcB&gclsrc=aw.ds)
<br>
백엔드 :  [Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/)
	
<br>
		
### 개발기간

2022.08 ~ 2022.12 (4개월)

<br>

### Running from source

터미널에 이 명령어들을 입력해 실행해보세요 :

```sh
git clone https://github.com/StudyingTimer/Flutter-StudyingTimer.git
cd Flutter-Studyingtimer
flutter doctor
flutter pub get
flutter run
```

Flutter doctor에서 오류가 난다면 구글링 후 해결

<br>
		
## 기능

- 로그인  [bsmOauth(부산소프트웨어마이스터고 전용 로그인)](https://auth.bssm.kro.kr/oauth?clientId=5f034939&redirectURI=http://localhost:3000/oauth) 소셜로그인 (완료)
- 회원가입 닉네임생성 (완료)
- Timer (완료)
- Todo List (완료)
- 랭킹 (미완)
- 통계 (미완)
		
<br>
		
## 배운점 & 아쉬운점
		
### 배운점
 - 백엔드의 기본적인 구조 이해
 - JPA, Lombok 등 Spring의 핵심적 기능 이해
 - 백엔드에서 프론트엔드까지의 흐름
 - Oauth 소셜 로그인
		
### 아쉬운점
 - 백엔드가 처음이라 초반에 애를 많이 먹었다는 점
 - 독자적인 로그인 및 회원가입 기능이 없다는 점
 
 
## Git upload Rules

- [CREATE] 새로 만들었을때
- [UPDATE] 만들어진 것에서 수정이나 변동사항이 생겼을때
- [ADD] 무언가가 추가 되었을때
- [DELETE] 만들어진 것을 삭제 했을때
- [REFACTORING] 코드를 리팩토링 했을때
- [FIX] 오류를 확인하고 고쳤을때
- [REPLACE] 파일을 옮겼을 때