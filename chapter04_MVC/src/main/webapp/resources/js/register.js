// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/register.css';

// 2. link 태그 생성 
//<link rel="stylesheet" href="/resources/css/boardList.css">

let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;

// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// 각 버튼 클릭 이벤트
// 각 눌린 버튼의 id를 가져와서 분기

//--- 버튼 클릭 이벤트
//<button type="button" class="btn btn-fir" id="registerBtn">새 게시글 등록</button>
let f = document.querySelector("form");

document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener("click", ()=>{
		let type = btn.id;
		
		switch(type){
			case 'registerBtn':
				register();
				break;			
				
			case "resetBtn":
				f.reset();
				break;
				
			case "indexBtn":
				const {pageNum, amount} = getStorageData();
				const sendData = `pageNum=${pageNum}&amount=${amount}`;
				location.href = '/board/list?' + sendData;
				break;
		}
		
	});
});

function register(){
	// 검증
	if(f.title.value == '' || f.writer.value == '' || f.content.value == ''){
		alert("내용을 입력하세요.");
		return;
	}
	
	let str=``;
	let liElements = document.querySelectorAll(`.uploadResult ul li`);
	liElements.forEach((li, index) => {
		let path = li.getAttribute('path');
		let uuid = li.getAttribute('uuid');
		let fileName = li.getAttribute('fileName');
		
		str += `<input type="hidden" name="attachList[${index}].uploadPath" value="${path}"/>`;
		str += `<input type="hidden" name="attachList[${index}].uuid" value="${uuid}"/>`;
		str += `<input type="hidden" name="attachList[${index}].fileName" value="${fileName}"/>`;
		
	});
	
	//f.innerHTML += str;    //데이터 초기화 O
	f.insertAdjacentHTML('beforeend', str);   // 데이터 초기화 X
	
	// 폼 내용을 post로 전송
	f.action = '/board/register';
	//f.method='POST';
	f.submit();	// post 방식으로 컨트롤러의 register로  값을 들고 접근
}
