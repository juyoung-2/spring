// .header 하위에 있는  a 태그들에 버튼 클릭 이벤트 부여
// 기본 이벤트 방지
// 해당 속성에서 값 꺼내서 분기 태우기
document.querySelectorAll(".header a").forEach(a => {
  a.addEventListener("click", (e) => {
	  e.preventDefault();   // a 태그 기본 동작 제거
	  
	  // href 속성을 가져오려면 e.currentTarget.getAttribute('href')를 쓰는 게 맞음.  
	  // e.target은 "실제 클릭된 요소"를 다루고 싶을 때 사용.
	  let menu = e.target.getAttribute('href');
	  if(menu === "mainPage"){location.href = '/';}
	  else if(menu === "boardList"){location.href = '/board/list';}
  });
});

function setStorageData(pageNum, amount){
	let pageData = {
		pageNum : pageNum,
		amount : amount
	};
	localStorage.setItem('page_data', JSON.stringify(pageData));
}

function getStorageData(){
	return JSON.parse(localStorage.getItem('page_data'));
}

