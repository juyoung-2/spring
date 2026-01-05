package org.joonzis.mapper;

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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {
   @Autowired
   private BoardMapper mapper;
   
   @Test
   public void testGetList(Criteria cri) {
      List<BoardVO> list = mapper.getList(cri);
      for(BoardVO vo : list) {
         log.info(vo);
      }
      // mapper.getList().forEach(vo -> log.info(vo)); // 최적화
   }
   
   @Test
   public void testInsert() {
       BoardVO vo = new BoardVO();
       vo.setTitle("제목");
       vo.setContent("내용");
       vo.setWriter("user");

       int result = mapper.insert(vo);
       log.info("insert result: " + result);
   }

   @Test
   public void testRead() {
      BoardVO vo = mapper.read(1); // bno=1
      log.info(vo);
   }
   
   @Test
   public void testDelete() {
      int result = mapper.delete(9); // bno=7
      log.info("delete count: " + result);
   }
   
   @Test
   public void testUpdate() {
      BoardVO vo = new BoardVO();
      vo.setBno(1);
      vo.setTitle("수정제목입니다");
      vo.setContent("수정내용입니다");
      vo.setWriter("userupdate");
      
      int result = mapper.update(vo);
      log.info("update result: " + result);
   }
}

