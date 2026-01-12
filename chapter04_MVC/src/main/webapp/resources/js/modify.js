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

//기존 첨부 파일 수정 페이지로 가져오기
const fileInput = document.querySelector("input[name='uploadFile']");
const uploadResult = document.querySelector(".uploadResult ul");
const bno = document.querySelector('input[name="bno"]').value;

(function(){
	fetch(`/board/getAttachList/${bno}`)
		.then(response => response.json())
		.then(data => {
			showUploadedFile(data);
		})
		.catch(err => console.log(err));
})();

// 수정 페이지 파일 업로드

fileInput.addEventListener('change', e => {

	const formData = new FormData();
	const files = e.target.files;

	for(let i = 0; i < files.length; i++){
		formData.append("uploadFile", files[i]);
	}

	fetch('/uploadAsyncAction', {
		method: 'post',
		body: formData
	})
	.then(res => res.json())
	.then(data => {
		console.log("upload result:", data);
		showUploadedFile(data);
	})
	.catch(err => console.log(err));
});

// 수정 페이지 파일 목록 삭제
function showUploadedFile(uploadResultArr){
	let str = '';

	uploadResultArr.forEach(file => {

		let fileCallPath = encodeURIComponent(
			file.uploadPath +
				"/" +
			file.uuid + "_" +
			file.fileName
		);

		str += `<li data-path="${file.uploadPath}"
		              data-uuid="${file.uuid}"
		              data-filename="${file.fileName}">`;

		str += `<a href="/download?fileName=${fileCallPath}">`;
		str += `${file.fileName}`;
		str += `</a>`;
		str += `<span class="removeFile"> X </span>`;
		str += `</li>`;
	});

	uploadResult.innerHTML = str;
}

//수정 페이지 파일 삭제
uploadResult.addEventListener('click', e => {

	if(!e.target.classList.contains('removeFile')) return;

	// 화면에서만 제거
	const li = e.target.closest('li');
	const fileName =
		li.dataset.path + "/" +
		li.dataset.uuid + "_" +
		li.dataset.filename;

	fetch('/deleteFile', {
		method : 'post',
		body : encodeURIComponent(fileName),
		headers : { 'Content-Type' : 'text/plain' }
	})
	.then(res => {
		if(res.ok){
			li.remove();
		}else{
			alert('파일 삭제 실패');
		}
	});
});

// 수정 버튼
document.querySelector('#modifyBtn').addEventListener('click', e => {

	const form = document.querySelector('#modifyForm');
	const liTags = uploadResult.querySelectorAll('li');
	let str = '';

	liTags.forEach((li, i) => {
		str += `<input type="hidden" name="attachList[${i}].uploadPath" value="${li.dataset.path}">`;
		str += `<input type="hidden" name="attachList[${i}].uuid" value="${li.dataset.uuid}">`;
		str += `<input type="hidden" name="attachList[${i}].fileName" value="${li.dataset.filename}">`;
	});

	form.insertAdjacentHTML('beforeend', str);
	form.submit();
});





