# I Hate Plugin
What is this plugin?

## 기능
"튜닝의 끝은 순정이다." - 누군가

원래 기능 더 많았는데 되던것도 안돼서 때려침

### 악명의 변동 조건(플레이어 목록에서 확인 가능)
- 주민을 죽일 시 1 증가
- 엔더 드래곤, 위더를 죽일 시 1 감소
- 플레이어를 죽일 시 상대 악명 스택에 따라 증가 또는 감소:
  <br>상대의 악명이 양수라면, 그 수치만큼 감소
  <br>상대의 악명이 0 또는 음수라면, 그 수치의 절댓값 + 1 만큼 증가
- 어떤 방식으로든 사망 시 악명 초기화

### 악명의 효과
- -3 이하: 영구적인 저항 I, 성급함 I 효과
- 1: 받는 피해 10% 증가(최소 1)
- 2: 주는 피해 10% 감소(최소 1)
- 3: 받는 피해/주는 피해 25% 증가/감소(최소 2.5), 악명이 1, 2일 때 효과를 대체함.
- 4: 영구적인 허기 I 효과
- 5: 영구적인 구속 I 효과
- 10 이상: 영구적인 발광 I 효과

### 몹 전리품
- 플레이어: 뼈 4-8개, 돼지고기 3-6개, 플레이어에게 사망 시 머리 1개(약탈 마법부여 영향 없음)
- 주민: 뼈 4-8개, 돼지고기 3-6개
- 말: 뼈 2-4, 소고기 3-5개
- 늑대: 뼈 1-3개, 양고기 1-3개
- 돌고래, 거북이: 뼈 2-7개, 양고기 2-5개
- 팬텀, 앵무새: 뼈 1-2개, 돼지고기 1-2개
- 엔더 드래곤, 위더: 뼈 10-30개, 돼지고기 15-40개, 소고기 15-40개


## 라이선스
이 프로젝트는 [LICENSE](LICENSE) 파일의 전문에 따라 MIT 허가서가 적용됩니다.
<br>라이선스 및 저작권 고지 하에 개인적 이용, 수정, 배포, 상업적 이용이 가능하며 보증 및 책임을 지지 않습니다.

## 크레딧
- stleary의 [JSON-java](https://github.com/stleary/JSON-java)([퍼블릭 도메인](https://github.com/stleary/JSON-java/blob/master/LICENSE))
- JetBrains의 [kotlin](https://github.com/JetBrains/kotlin)([아파치 라이선스 2.0](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt))

****

## 업데이트 로그

### 2.1.0
- 마인크래프트 1.19.4로 업그레이드.


### 2.0.0
- 마인크래프트 1.19.2로 업그레이드.

### 1.0.1
- 서버의 스코어보드가 보이지 않는 문제 수정.
- 개발자가 귀찮은 관계로 추가로 개발 중이던 여러 기능 삭제.

### 1.0.0
- 최초 제작.