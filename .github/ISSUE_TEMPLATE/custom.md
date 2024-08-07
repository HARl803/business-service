---
name: Custom issue template
about: Describe this issue template's purpose here.
title: ''
labels: ''
assignees: cocorini

---

## Description
- 

## Todo
- [x] 공지사항을 등록, 단일조회, 전체조회, 수정, 삭제할 수 있는 `controller`를 구현합니다.
- [x]  `/api/notice`에 매핑되는 `controller`를 구현합니다.
    - [x] 관리자가 로그인된 상태에서 공지사항을 등록, 수정, 삭제 기능
    - [x] 로그인 되어있지 않은 모든 유저가 공지사항을 확인할 수 있도록 조회 기능
    - [x] RequestDto와 ResponseDto로 나누어 구현
- [x] 메인로직을 담은 `service`를 작성합니다.
    - [x] 받아온 인증정보가 유효한지 검증하고, 유효한 데이터가 아닐 경우  `custom exception`을 발생합니다.
    - [x] 입력받은 제목과 내용으로 공지사항을 등록 및 수정합니다. 
    - [x] 공지사항 아이디로 공지사항을 찾고, 찾지 못할경우 `custom exception`을 발생합니다.
    - [x] 공지사항 아이디로 특정 공지사항을 삭제합니다.
- [x] mybatis를 활용하여 공지사항 아이디로 공지사항 정보를 찾습니다.
- [x] swagger에서 보기 편하도록 api별로 태그와 메소드별 설명을 추가합니다.

## Etc
