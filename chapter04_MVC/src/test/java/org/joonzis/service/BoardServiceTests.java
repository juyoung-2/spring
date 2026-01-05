package org.joonzis.service;


import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
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
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
	@Test
	public void TestGetList(Criteria cri) {
		
	// = service.getList().forEach(vo -> log.info(vo));
		
		List<BoardVO> list = service.getList(cri);
		for(BoardVO vo : list) {
			log.info(vo);
		}
	}
	
	@Test
	public void TestRegister() {
		BoardVO vo = new BoardVO();
		vo.setTitle("new title");
		vo.setContent("new content");
		vo.setWriter("주영");
		
		service.register(vo);
		log.info("삽입된 글: " + vo);
	}
	
	@Test
	public void TestGet() {
		//String result = service.remove(1) ? "성공" : "실패";
		BoardVO vo = service.get(1);
		log.info(vo);
	}
	
	@Test
	public void TestRemove(){
		boolean count = service.remove(1);
		log.info("삭제된 건수 : " + count);
	}
	
	@Test
	public void TestModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(2); // PK 2번 글 수정
        vo.setTitle("수정된 제목");
        vo.setContent("수정된 내용");
        vo.setWriter("주영");

        boolean count = service.modify(vo);
        log.info("수정된 건수: " + count);
	}
	
	
	
	
	
	
}
