package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardVO;
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
public class BoardMapperTests {
	@Autowired
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = mapper.getList();
//		for(BoardVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//    public void testInsert() {
//        BoardVO vo = new BoardVO();
//        vo.setTitle("새 글 제목");
//        vo.setContent("새 글 내용");
//        vo.setWriter("주영");

//        mapper.insert(vo);
//        log.info("삽입된 글: " + vo);
//    }

    @Test
    public void testRead() {
        BoardVO vo = mapper.read(1); // PK 1번 글 조회
        log.info(vo);
    }

    @Test
    public void testDelete() {
        int count = mapper.delete(1); // PK 1번 글 삭제
        log.info("삭제된 건수: " + count);
    }

    @Test
    public void testUpdate() {
        BoardVO vo = new BoardVO();
        vo.setBno(2); // PK 2번 글 수정
        vo.setTitle("수정된 제목");
        vo.setContent("수정된 내용");
        vo.setWriter("주영");

        int count = mapper.update(vo);
        log.info("수정된 건수: " + count);
    }

}

















