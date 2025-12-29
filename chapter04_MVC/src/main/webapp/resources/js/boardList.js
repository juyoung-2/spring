// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = ['/resources/css/boardList.css',
					   '/resources/css/page.css'];

// 2. link 태그 생성 
//<link rel="stylesheet" href="/resources/css/boardList.css">

cssBinding(CSS_FILE_PATH);

function cssBinding(cssFiles){
	cssFiles.forEach(css =>{
		let linkEle = document.createElement('link');
		linkEle.rel = 'stylesheet';
		linkEle.type = 'text/css';
		linkEle.href = css;
		// 3. head 태그에 link 엘리먼트 추가
		document.head.appendChild(linkEle);
	});
}

// --- 버튼 클릭 이벤트
//<button type="button" class="btn btn-fir" id="registerBtn">새 게시글 등록</button>
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener("click", ()=>{
		let type = btn.id;
		if(type === 'registerBtn'){
			location.href = '/board/register';
		}
	});
});

// --- 제목 클릭 이벤트(get 이동)
// 클릭 대상들 판단
// 기본 이벤트 방지
// 전달 받은 데이터(bno) 컨트롤러 전달

document.querySelectorAll("td a").forEach(a => {
	a.addEventListener("click", (e)=>{
		e.preventDefault();   // a 태그 기본 동작 제거
		const num = a.getAttribute('href');
		location.href = `/board/get?bno=${num}`;
	});
});

// 페이지 이동 등에서 사용하기 위한 객체 cri를 저장하는 방법

let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');
if(!pageNum || !amount){
	pageNum = 1;
	amount = 15
}
setStorageData(pageNum, amount);

// --- 페이지 번호 클릭 이벤트
document.querySelectorAll('.page-nation li a').forEach(aEle =>{
	aEle.addEventListener('click', (e)=>{
		// 기존 이벤트 방지 (href)
		e.preventDefault();
		//let pageNum = e.target.getAttribute("href");
		let pageNum = aEle.getAttribute("href");
		
		setStorageData(pageNum, amount);
		// let sendData = 'pageNum='+pageNum+'&amount='+amount;
        let sendData = `pageNum=${pageNum}&amount=${amount}`;
      	// location.href = '/board/list?pageNum='+pageNum+'&amount=15';
      	location.href = '/board/list?' + sendData;
		

	});
});


