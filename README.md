# super-duper-giggle

꼭 OpenCV 필요

# Android AutoClicker Example

이 프로젝트는 Android에서 동작하는 **자동 클릭기 예제**입니다.  
교육 및 학습용으로 만들어진코드입니다.

---

## 기능

- 버튼 클릭으로 실행/중지
- 백그라운드에서 화면 캡처 및 템플릿 매칭
- OpenCV를 이용한 이미지 처리
- 0.1초 주기 반복 실행

---

## 요구 사항

- Android 5.0(Lollipop, API 21) 이상
- OpenCV Android SDK 설치 필요
- USB 디버깅 활성화된 실제 기기 필요
- MediaProjection 권한 허용 필요

---

## 설치 방법

1. Android Studio로 프로젝트 열기
2. OpenCV Android SDK 모듈 추가 (`app/libs` 또는 프로젝트 모듈로)
3. Gradle Sync
4. Build → Build APK(s) → Locate APK
5. 실제 Android 기기에 설치

---

## 사용 방법

1. 앱 실행 후 **시작 버튼 클릭**
   - MediaProjection 권한 요청 → 허용
   - Foreground Service가 시작되며 백그라운드 실행
2. 앱 종료 또는 **중지 버튼 클릭** → 서비스 종료

---

## 주의사항

- MediaProjection API 사용으로 화면 캡처 기능 필요
- 자동 클릭 기능은 학습용 예제이며, 실제 게임/앱에 사용 시 계정 정지 등의 위험 있음
- OpenCV 라이선스 준수 필요

---

## 라이선스

MIT License
-----------------------

1.	버튼 클릭으로 시작/중지 가능
	2.	Foreground Service에서 백그라운드 동작
	3.	MediaProjection API로 화면 캡처
	4.	OpenCV로 템플릿 매칭
	5.	0.1초 주기 반복 (delay(100))

 6.	app/
 └─ src/main/
     ├─ java/com/example/autoclicker/
     │     ├─ MainActivity.kt
     │     ├─ ScreenshotService.kt
     │     ├─ Screenshot.kt
     │     ├─ Assets.kt
     │     └─ MultiTemplateMatch.kt
     ├─ res/layout/activity_main.xml
     └─ AndroidManifest.xml

코드는 Kotlin + OpenCV Android SDK + MediaProjection API 기준입니다.

순서
 1.AndroidManifest.xml
 2.activity_main.xml
 3.MainActivity.kt
 4.ScreenshotService.kt
 5.Screenshot.kt
 6.Assets.kt & MultiTemplateMatch.kt

 이 Android 자동 클릭기 앱은 실제 Android 기기에서 실행해야 합니다. PC나 일반 Java 환경에서는 MediaProjection API와 OpenCV Android SDK를 사용할 수 없기 때문에 동작하지 않습니다.

실행 방법을 단계별로 정리하면 다음과 같습니다.

⸻

1️⃣ Android Studio에서 프로젝트 열기
	•	위에서 만든 프로젝트 구조를 그대로 Android Studio에 넣습니다.
	•	build.gradle에 OpenCV Android SDK 추가:
     implementation project(':openCVLibrary') // OpenCV SDK 모듈로 추가했을 경우
     minSdkVersion은 21 이상 권장 (MediaProjection API 필요)

⸻

2️⃣ 디바이스 연결
	•	USB로 실제 Android 기기 연결
	•	USB 디버깅 켜기 (설정 → 개발자 옵션 → USB 디버깅)
	•	에뮬레이터에서는 MediaProjection API 일부 기능이 제한되므로 실제 기기 필수

⸻

3️⃣ 앱 빌드 & 설치
	•	Android Studio에서 Run 클릭
	•	앱이 연결된 기기에 설치되고 자동 실행

⸻

4️⃣ 앱 사용
	1.	앱 실행 → 시작 버튼 클릭
	•	MediaProjection 권한 요청 창 뜸 → 허용
	•	Foreground Service가 시작되며 백그라운드에서 스크린샷 + 템플릿 매칭 실행
	2.	앱 종료 또는 중지 버튼 클릭 → 서비스 종료

⸻

🔹 참고
	•	화면 캡처 기능은 Android 5.0(Lollipop) 이상 필요
	•	백그라운드에서 계속 실행하려면 Foreground Service 필수
	•	앱이 실행 중인 동안에는 화면에 오버레이 UI나 스크린샷 기능이 작동 가능
