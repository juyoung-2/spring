// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/get.css';

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
	let bno = myForm.bno.value;
	location .href = `/board/modify?bno=${bno}`;
}

// ------------댓글 관련 스크립트-------------------------
const rs = replyService;  //reply.js에서 CRUD 담당 객체

// 댓글 등록하기
//rs.add(
//	{
//		bno : f.bno.value,
//		reply : 'JS TEST',
//		replyer : 'tester'
//	},
//	function(result){
//		console.log(result);
//	}
//);

// 댓글 리스트 가져오기
//rs.getList(f.bno.value, function(result){
//	console.log(result);
//});


// 댓글 삭제하기
//rs.remove(11, function(result){
//	console.log(result);
//})

// 댓글 수정하기
//rs.update(2, {reply : '수정된 댓글입니다'}, function(result){
//	console.log(result);
//})

// 댓글 조회하기
rs.get(8, function(result){
	console.log(result);
})

