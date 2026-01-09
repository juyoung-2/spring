package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardAttachMapperTests {
   @Autowired
   private BoardAttachMapper mapper;
   
   @Test
   public void testFindByBno() {
	  int bno = 1;
      List<BoardAttachVO> list = mapper.findByBno(bno);
      for(BoardAttachVO vo : list) {
         log.info(vo);
      }
      // mapper.getList().forEach(vo -> log.info(vo)); // 최적화
   }
   
   @Test
   public void testInsert() {
	   BoardAttachVO vo = new BoardAttachVO();
       vo.setUuid("test");
       vo.setUploadPath("test");
       vo.setFileName("tset");
       vo.setBno(1);

       mapper.insert(vo);
       
   }

   
   @Test
   public void testDelete() {
	   String uuid = "test";
       mapper.delete(uuid); // bno=7
      
   }
   
   
}

