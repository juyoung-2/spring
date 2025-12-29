// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/modify.css';

// 2. link 태그 생성 
//<link rel="stylesheet" href="/resources/css/boardList.css">

let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;

// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// --- 버튼 동작
let f = document.querySelector("form");
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener("click", ()=>{
		let type = btn.id;

		switch(type){
			case 'modifyBtn':
				modify();
				break;		
				
			case "removeBtn":
				remove();
				break;
				
			case "indexBtn":
				const {pageNum, amount} = getStorageData();
				const sendData = `pageNum=${pageNum}&amount=${amount}`;
				location.href = '/board/list?' + sendData;
				break;
		}
	});
});

let myForm = document.querySelector("form");
function modify(){
	if(myForm.title.value == '' || myForm.content.value == ''){
		alert("내용을 입력하세요.");
		return;
	}
	myForm.action = '/board/modify';
	myForm.submit();
}


function remove(){
	/*
	const bnoEle = myForm.bno
	myForm.innerHTML = '';
	myForm.appendChild(bnoEle);*/
	
	myForm.action="/board/remove";
	myForm.submit();
	
}



