// 로그인 상태 확인 (예: 사용자가 로그인되어 있다고 가정)
var isLoggedIn = true; // true는 로그인된 상태, false는 로그인되지 않은 상태로 가정

// 메뉴 항목 업데이트 함수
function updateMenu() {
    // 메뉴 항목 업데이트
    if (isLoggedIn) {
        // 로그인된 경우
        document.getElementById("sub-menu-login").innerHTML = '<a href="/logout">Log Out</a>';
        document.getElementById("sub-menu-join").innerHTML = '<a href="/mypage">My Page</a>';
        document.getElementById("sub-menu-cart").innerHTML = '<a href="/cart">Cart</a>';
    } else {
        // 로그인되지 않은 경우
        document.getElementById("sub-menu-login").innerHTML = '<a href="/login">Login</a>';
        document.getElementById("sub-menu-join").innerHTML = '<a href="/join">Join</a>';
        document.getElementById("sub-menu-cart").innerHTML = '<a href="javascript:showLoginAlert();">Cart</a>';
    }
}

// 카트를 클릭할 때 로그인 경고 표시 함수
function showLoginAlert() {
    alert("로그인이 필요합니다. 로그인 후에 카트를 이용할 수 있습니다.");
}

// 페이지 로드 시 메뉴 업데이트 실행
window.onload = updateMenu;