## 기능목록

### 지하철역/노선 관리 기능

- [x] 기존에 존재하는 지하철역 이름으로 등록을 시도하면 400을 반환하는 기능
- [x] 노선 생성 기능
- [x] 노선 목록 조회 기능
- [x] 노선 조회 기능
- [x] 노선 수정 기능
- [x] 노선 삭제 기능

### 2단계 프레임워크 적용

- [x] h2, schema 설정
- [x] Service Layer 구현
    - [x] StationService 구현
    - [x] LineService 구현

- [x] 스프링 JDBC를 적용
    - [x] Station Repository
        - [x] Save, Exist 기능 구현
        - [x] get 기능 구현
        - [x] delete 기능 구현
    - [x] Line Repository
        - [x] 이름이랑 색깔을 입력받아 Line 생성기능 구현
        - [x] Line이 존재하는지 확인하는 기능 구현
        - [x] 전체 Line 리스트 조회 기능 구현
        - [x] id를 통해 특정 Line 조회 기능 구현
        - [x] id를 통해 특정 Line 정보 수정 기능 구현
        - [x] id를 통해 특정 Line 삭제 기능 구현