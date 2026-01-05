package org.joonzis.controller;


import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	// 1. 등록
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		log.info("ReplyVO : " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply Insert Count : " + insertCount);
		
		return insertCount == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) :
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 2. 목록(페이지)
	// /reply/pages/:bno - get
	@GetMapping(value = "/pages/{bno}",
				produces = {
						MediaType.APPLICATION_XML_VALUE,
						MediaType.APPLICATION_JSON_VALUE
				})
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") int bno) {
		log.info("getList : " + bno);
		List<ReplyVO> list = service.getList(bno);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 3. 삭제
	// reply/:rno - delete
	@DeleteMapping(value="/{rno}", 
				produces=MediaType.TEXT_PLAIN_VALUE)
	   public ResponseEntity<String> remove(@PathVariable("rno") int rno) {
	      log.info("remove : " + rno);
	      
	      boolean removeCheck = service.remove(rno);
	      log.info("Reply Delete Check : " + removeCheck);
	      
	      return removeCheck == true ?
	            new ResponseEntity<String>("success", HttpStatus.OK) :
	            new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	
	// 4. 조회
	// reply/:rno - get
	@GetMapping(value = "/{rno}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE	
				})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno){
		log.info("get..." + rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	
	// 5. 수정
	// reply/:rno - put or patch
	@RequestMapping(
			value = "/{rno}",
			method = {RequestMethod.PUT, RequestMethod.PATCH},
			consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		log.info("rno : " + rno);
		log.info("ReplyVO : " + vo);
		vo.setRno(rno);
		
		boolean modifyCount = service.modify(vo);
		
		log.info("Reply Modify Count : " + modifyCount);
		
		return modifyCount == true ?
				new ResponseEntity<String>("success", HttpStatus.OK) :
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}
