// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = ['/resources/css/get.css',
	   				'/resources/css/modal.css'];

// 2. link 태그 생성 
//<link rel="stylesheet" href="/resources/css/boardList.css">

//let linkEle = document.createElement('link');
//linkEle.rel = 'stylesheet';
//linkEle.type = 'text/css';
//linkEle.href = CSS_FILE_PATH;

cssBinding(CSS_FILE_PATH);

function cssBinding(cssFiles){
	cssFiles.forEach(css =>{
		let linkEle = document.createElement('link');
		linkEle.rel = 'stylesheet';
		linkEle.type = 'text/css';
		linkEle.href = css;
		document.head.appendChild(linkEle);
	});
}

// 3. head 태그에 link 엘리먼트 추가
//document.head.appendChild(linkEle);

// --- 버튼 이벤트 동작
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
				
			case "replyBtn": {
				// 댓글 등록 창
			    registerModalPage();   // 함수 직접 호출
			    break;
			} 
		    case "addReplyBtn":
		    	// 진짜 댓글 등록 실행 버튼
		    	registerReply();
		    	break;
			
		    case "closeModalBtn" :
		    	closeModal();
		    	break;
		    	
		    case "modifyReplyBtn":
		        modifyReply();
		        break;
		        
		    case "removeReplyBtn":
		    	removeReply();
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

function showList(){
	let bno = f.bno.value;
	let replyUL = document.querySelector('.chat');
	
	rs.getList(bno, jsonArry => {
	let msg = '';
	jsonArry.forEach(reply => {
		 if(!reply){
	        replyUL.innerHTML = '';
	        return;
	     }
	     msg += `<li data-rno="${reply.rno}" 
	    	 		 data-reply="${reply.reply}" 
				     data-replyer="${reply.replyer}" 
				     data-replydate="${reply.replydate}" 
	    	 		 onclick="modifyModalPage(this)">`;
	     msg += `<div>`;
	     msg += `<div class= "chat-header">`;
	     msg += `<strong>${reply.replyer}</strong>`;
	     msg += `<small class="pull-right">${displayTime(reply.replydate)}</small>`;
	     msg += `</div>`;
	     msg += `<p>${reply.reply}</p>`;
	     msg += `</div>`;
	     msg += `</li>`;
	         
      });
      replyUL.innerHTML = msg;
   });
}
showList();
function displayTime(unixTimeStamp){
	// 초 일 때
	let myDate = new Date(unixTimeStamp);
	// 밀리초 일 때
// let myDate = new Date(unixTimeStamp/1000);
	
	let y = myDate.getFullYear();
	let m = String(myDate.getMonth()+1).padStart(2,'0');
	let d = String(myDate.getDate()).padStart(2,'0');
	
	let date = y + '-' + m + '-' + d;
	return date;
}


// --------------- 모달 관련 스크립트----------

const modal = document.querySelector('#modal');
const inputReply = document.querySelector('input[name="reply"]');
const inputReplyer = document.querySelector('input[name="replyer"]');
const inputReplydate = document.querySelector('input[name="replydate"]');
const addReplyBtn = document.querySelector('#addReplyBtn');
const modifyReplyBtn = document.querySelector('#modifyReplyBtn');
const removeReplyBtn = document.querySelector('#removeReplyBtn');
const closeModalBtn = document.querySelector('#closeModalBtn');
function openModal(){
	modal.style.display = "block";
// document.body.style.overflow = "hidden";
}
function closeModal(){
	modal.style.display = "none";
// document.body.style.overflow = "auto";
}

// 댓글 등록 창 함수
function registerModalPage(){
	// 보여질 목록 수정
	regReplyModalStyle();
	// 입력 내용 초기화& 불러오기
	inputReply.value = '';
	inputReplyer.value = '';
	
	
	openModal();
}
// 댓글 달기 창 스타일 변경 함수
function regReplyModalStyle(){
//	modifyReplyBtn.class = 'hide';
	addReplyBtn.classList.remove('hide');
	
	modifyReplyBtn.classList.add('hide');
	removeReplyBtn.classList.add('hide');
	inputReplydate.closest('div').classList.add('hide');
	
	inputReplyer.readOnly = false;
	inputReplydate.readOnly = false;
}
// 진짜 댓글 등록 함수
function registerReply(){
	if(!inputReply.value || !inputReplyer.value){
		alert("모든 내용을 입력하세요");
		return;
	}
	
	const sendData = {
		reply : inputReply.value,
		replyer : inputReplyer.value,
		bno : f.bno.value
	};
	rs.add(sendData, result =>{
		console.log(result);
		closeModal();
		showList();
	});
}
// 댓글 클릭 함수
let rno;
function modifyModalPage(t){
	// 보여질 목록 수정
	modReplyModalStyle();
	rno = t.dataset.rno;
	
	inputReply.value = t.dataset.reply;
	inputReplyer.value = t.dataset.replyer;
	inputReplydate.value = displayTime(Number(t.dataset.replydate));
	
	
	// 입력 내용 초기화 & 불러오기
	
	openModal();
}

function modifyReply(){
	const reply = {
			reply : inputReply.value,
			replyer : inputReplyer.value,
			replydate : inputReplydate.value,
			bno : f.bno.value
		};
		rs.update(rno, reply, result =>{
			console.log(result);
			closeModal();
			showList();
		});
}
function modReplyModalStyle(){
	
	addReplyBtn.classList.add('hide');
	
	modifyReplyBtn.classList.remove('hide');
	removeReplyBtn.classList.remove('hide');
	inputReplydate.closest('div').classList.remove('hide');

	inputReplyer.readOnly = true;
	inputReplydate.readOnly = true;
}


function removeReply(event){
	
	
	if(!confirm("삭제하시겠습니까?"))return;
	
	rs.remove(rno, result => {
		console.log(result);
		if(result === 'success'){
			closeModal();
			showList();
			alert("댓글이 삭제되었습니다.");
		} else {
			alert("댓글 삭제 실패");
		}
	})
		
}





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
//rs.get(8, function(result){
//	console.log(result);
//})
	




















