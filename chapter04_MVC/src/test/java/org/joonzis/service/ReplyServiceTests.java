package org.joonzis.service;


import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTests {

	@Autowired
	private ReplyService service;
	
	@Test
	public void TestGetList() {
		
		int bno = 1;
		List<ReplyVO> list = service.getList(bno);
		for(ReplyVO vo : list) {
			log.info(vo);
		}
	}
	
	@Test
	public void TestRegister() {
		ReplyVO vo = new ReplyVO();
		  vo.setBno(32);
	      vo.setReply("테스트 댓글");
	      vo.setReplyer("tester");
		
		service.register(vo);
		log.info("삽입된 글: " + vo);
	}
	
	@Test
	public void TestGet() {
		//String result = service.remove(1) ? "성공" : "실패";
		ReplyVO vo = service.get(1);
		log.info(vo);
	}
	
	@Test
	public void TestRemove(){
		boolean count = service.remove(1);
		log.info("삭제된 건수 : " + count);
	}
	
	@Test
	public void TestModify() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(2); // PK 2번 글 수정
		vo.setRno(23);
	    vo.setReply("수정된 댓글 입니다");
        boolean count = service.modify(vo);
        log.info("수정된 건수: " + count);
	}
	
	
	
	
	
	
}
