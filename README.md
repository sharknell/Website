<div>
<body>
    <h1> Web Project </h1>
    <p>이 프로젝트는 전자상거래 웹사이트를 개발하는 2차 프로젝트입니다. 아래는 프로젝트에 참여한 팀 멤버와 각자의 역할에 대한 정보입니다.</p>
    <ul>
        <li><strong>백승현&lt;PM&gt;</strong> - Product 및 Detail 페이지 개발</li>
        <li><strong>이교진</strong> - User Information 관련 기능 개발</li>
        <li><strong>이승민</strong> - QnA 및 Review 기능 개발</li>
        <li><strong>이도형</strong> - View 개발</li>
        <li><strong>김규호</strong> - 데이터베이스 관리 (Maria DB)</li>
    </ul>

- **프로그래밍 언어**: [Spring Boot]
- **데이터베이스**: [Maria DB]
- **개발환경**: [Eclipse IDE][Intelli J]
</div>
    <h2>작업 내역</h2>
    <ul>
        <li><strong>0905 이교진</strong> - MemberController, MemberRepository, MemberService, MemberServiceImpl 생성 및 로그인, 회원 관리 join, login view에 대한 테스트 완료</li>
        <li><strong>0913 이교진</strong> - 보안 시스템 적용 및 테스트 완료</li>
        <li><strong>0914 이교진</strong> - 비밀번호 찾기 기능 추가 및 완료</li>
        <li><strong>2023.09.26</strong> - 형상 관리 완료</li>
    </ul>

    

    <h2>구현 예정 작업</h2>
    <p>1. 장바구니 (Cart) 기능 추가</p>
    <p>2. 컨틀롤러(Mapping) 버그 수정 예정</p>
    <p>3. 비로그인시 메인로고 안보이는 버그 수정 예정</p>

    상품DB에 임시로 인서트해서 테스트 할 때 사용하세요.
    INSERT INTO product (itemcount, category, description, imglink, imglink1, imglink2, imglink3, itemname, price, size, main_link, sizefit, asd) 
    VALUES (1, 'outers', null, 'top1.jpg', 'top2.jpg', 'top3.jpg', 'top4.jpg', 'Shwal Collar Coat Black', 429000, 'M,L', 'top5.jpg', 'asdsadsaffas', ' 1. 교환/반품 시 신청서 작성은 필수입니다.');
