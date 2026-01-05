console.log('reply module...');

// 자기 호출 함수
const replyService = (function(){
	
	function add(reply, callback){
		fetch('/reply/new', 
			{
				method : 'post',
				body : JSON.stringify(reply),
				headers : {
					'Content-type' : 'application/json; charset=utf-8'
				}
			}
		)
			.then(response => response.text())
			.then(data => {
				callback(data);
			})
			.catch(err => console.log(err));
	}
	
	function getList(bno, callback){
		fetch('/reply/pages/' + bno + '.json')
				.then(response => response.json())
				.then(data => {
					callback(data);
				})
				.catch(err => console.log(err));
	}
	
	function remove(rno, callback){
		fetch('/reply/' + rno, 
			{
			method : 'delete'
			})
		.then(response => response.text())
		.then(data => {
			callback(data);
		})
		.catch(err => console.log(err));
	}
	
	function update(rno, reply, callback){
		fetch('/reply/' + rno, 
				{
				method : 'put',
				body : JSON.stringify(reply),
				headers : {
					'Content-type' : 'application/json; charset=utf-8'
				}
				})
			.then(response => response.text())
			.then(data => {
				callback(data);
			})
			.catch(err => console.log(err));
	}
	
	function get(rno, callback){
		fetch('/reply/' + rno + '.json')
		.then(response => response.json())
		.then(data => {
			callback(data);
		})
		.catch(err => console.log(err));
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
		};
		
})();