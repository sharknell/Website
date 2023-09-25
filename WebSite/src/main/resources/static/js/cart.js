// 장바구니 데이터 배열
let cartItems = [];

// 상품 추가 버튼 클릭 이벤트 핸들러
document.querySelector('#add-to-cart-button').addEventListener('click', () => {
	const outer = {
		name: 'Embroidery Flower Shirt White',
		option: '옵션 : [M]',
		price: '₩189,000',
		quantity: 1,
		shipping: '기본구분',
		shippingFee: '무료',
	};

	addToCart(outer);
});

// 장바구니에 상품을 추가하는 함수
function addToCart(outer) {
	cartItems.push(outer);
	updateCart();
}

// 장바구니 목록을 업데이트하는 함수
function updateCart() {
	const cartTableBody = document.querySelector('.cart-items table tbody');

	// 기존 목록을 모두 제거
	while (cartTableBody.firstChild) {
		cartTableBody.removeChild(cartTableBody.firstChild);
	}

	// 장바구니 목록을 다시 그림
	cartItems.forEach((item) => {
		const row = document.createElement('tr');
		row.classList.add('cart-item');

		// 각 열을 생성하고 데이터를 설정
		const columns = [
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
		];

		columns[0].innerHTML = '<input type="checkbox" class="cart-item-checkbox">';
		columns[1].innerHTML = '<img src="outer1.jpg" alt="상품 이미지">';
		columns[2].innerHTML = `<h3>${item.name}</h3><p>${item.option}</p>`;
		columns[3].textContent = item.price;
		columns[4].innerHTML = `<p>수량: ${item.quantity}</p><div><button>+</button><button>-</button></div>`;
		columns[5].textContent = ''; // 적립금
		columns[6].textContent = item.shipping;
		columns[7].textContent = item.shippingFee;
		columns[8].textContent = item.price; // 합계
		columns[9].innerHTML = '<button class="checkout-button">주문하기</button><button class="checkout-button delete-button">삭제</button>';

		// 삭제 버튼 클릭 이벤트 핸들러
		columns[9].querySelector('.delete-button').addEventListener('click', () => {
			removeFromCart(item);
		});

		columns.forEach((column) => {
			row.appendChild(column);
		});

		cartTableBody.appendChild(row);
	});

	// 총 결제 금액 업데이트
	updateTotalPrice();
}

// 장바구니에서 상품을 삭제하는 함수
function removeFromCart(outer) {
	const index = cartItems.indexOf(outer);
	if (index !== -1) {
		cartItems.splice(index, 1);
		updateCart();
	}
}

// 총 결제 금액을 업데이트하는 함수
function updateTotalPrice() {
	const totalPriceElement = document.querySelector('.summary-table tbody tr td:nth-child(3)');
	// 아래 코드는 장바구니에 있는 상품 가격을 계산하여 업데이트해야 합니다.
	// 예제에서는 간단하게 0으로 설정합니다.
	const totalPrice = 0;
	totalPriceElement.textContent = `= ₩${totalPrice}`;
}

// 상품 추가 버튼 클릭 이벤트 핸들러
document.querySelector('#add-to-cart-button').addEventListener('click', () => {
	const outer = {
		name: 'Embroidery Flower Shirt White',
		option: '옵션 : [M]',
		price: '₩189,000',
		quantity: 1,
		shipping: '기본구분',
		shippingFee: '무료',
	};

	addToCart(outer);
});

// 장바구니에 상품을 추가하는 함수
function addToCart(outer) {
	cartItems.push(outer);
	updateCart();
}

// 장바구니 목록을 업데이트하는 함수
function updateCart() {
	const cartTableBody = document.querySelector('.cart-items table tbody');

	// 기존 목록을 모두 제거
	while (cartTableBody.firstChild) {
		cartTableBody.removeChild(cartTableBody.firstChild);
	}

	// 장바구니 목록을 다시 그림
	cartItems.forEach((item) => {
		const row = document.createElement('tr');
		row.classList.add('cart-item');

		// 각 열을 생성하고 데이터를 설정
		const columns = [
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
			document.createElement('td'),
		];

		columns[0].innerHTML = '<input type="checkbox" class="cart-item-checkbox">';
		columns[1].innerHTML = '<img src="outer1.jpg" alt="상품 이미지">';
		columns[2].innerHTML = `<h3>${item.name}</h3><p>${item.option}</p>`;
		columns[3].textContent = item.price;
		columns[4].innerHTML = `<p>수량: ${item.quantity}</p><div><button>+</button><button>-</button></div>`;
		columns[5].textContent = ''; // 적립금
		columns[6].textContent = item.shipping;
		columns[7].textContent = item.shippingFee;
		columns[8].textContent = item.price; // 합계
		columns[9].innerHTML = '<button class="checkout-button">주문하기</button><button class="checkout-button delete-button">삭제</button>';

		// 삭제 버튼 클릭 이벤트 핸들러
		columns[9].querySelector('.delete-button').addEventListener('click', () => {
			removeFromCart(item);
		});

		columns.forEach((column) => {
			row.appendChild(column);
		});

		cartTableBody.appendChild(row);
	});

	// 총 결제 금액 업데이트
	updateTotalPrice();
}


// 총 결제 금액을 업데이트하는 함수
function updateTotalPrice() {
	const totalPriceElement = document.querySelector('.summary-table tbody tr td:nth-child(3)');
	// 아래 코드는 장바구니에 있는 상품 가격을 계산하여 업데이트해야 합니다.
	// 예제에서는 간단하게 0으로 설정합니다.
	const totalPrice = 0;
	totalPriceElement.textContent = `= ₩${totalPrice}`;
}

// 삭제 버튼 클릭 시 호출될 함수
function deleteCartItem(button) {
	// 해당 버튼이 속한 행(tr)을 찾습니다.
	const row = button.closest('tr');

	if (row) {
		// 해당 행의 부모인 tbody를 찾습니다.
		const tbody = row.parentElement;

		// tbody를 삭제합니다.
		if (tbody) {
			tbody.remove();
		}
	}
}

// 삭제 버튼 클릭 시 호출될 함수
function deleteCartItem(button) {
	// 알림창을 띄워 사용자에게 삭제 여부를 확인
	const confirmed = confirm('정말 삭제하시겠습니까?');

	// 확인을 눌렀을 경우만 삭제 진행
	if (confirmed) {
		// 삭제 버튼이 속한 행(tr)을 찾습니다.
		const row = button.closest("tr");

		// 행을 삭제합니다.
		if (row) {
			row.remove();
		}
	}
}

// 삭제 버튼들을 찾아 이벤트 리스너를 추가합니다.
var deleteButtons = document.querySelectorAll(".checkout-button.delete-button");
deleteButtons.forEach(function(button) {
	button.addEventListener("click", function() {
		deleteCartItem(this);
	});
});

 // 페이지가 로드될 때 실행
  window.addEventListener('load', function () {
    // 각 상품의 가격을 가져와서 총 상품 금액 계산
    var cartItems = document.querySelectorAll('.cart-item');
    var totalouterPrice = 0;
    for (var i = 0; i < cartItems.length; i++) {
      var priceElement = cartItems[i].querySelector('.cart-item-price');
      var price = parseInt(priceElement.textContent.replace('₩', '').replace(',', ''));
      totalouterPrice += price;
    }

    // 배송비 계산 (여기서는 고정 배송비로 가정)
    var shippingFee = 4000;

    // 총 상품 금액과 배송비를 화면에 표시
    var totalouterPriceElement = document.querySelectorAll('.total-outer-price');
    for (var i = 0; i < totalouterPriceElement.length; i++) {
      totalouterPriceElement[i].textContent = '₩' + totalouterPrice.toLocaleString();
    }
    var shippingFeeElement = document.querySelectorAll('.shipping-fee');
    for (var i = 0; i < shippingFeeElement.length; i++) {
      shippingFeeElement[i].textContent = '₩' + shippingFee.toLocaleString();
    }

    // 결제 예정 금액 계산 및 화면에 표시
    var totalPriceElements = document.querySelectorAll('.total-price');
    for (var i = 0; i < totalPriceElements.length; i++) {
      var totalPrice = totalouterPrice + shippingFee;
      totalPriceElements[i].textContent = '₩' + totalPrice.toLocaleString();
    }
  });


