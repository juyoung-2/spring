package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith; // 테스트 실행 시 Spring 컨테이너를 먼저 띄움. 그래야 @Autowired 사용 가능해짐.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
/**
 * 실제 프로젝트에서 사용하는 root-context.xml을 그대로 불러옴
 * Spring 컨테이너가 먼저 실행됨
 * mapper bean 새성됨
 * @Autowired 사용 가능해짐
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// @Transactional // 모든 테스트 메서드에 트랜잭션 적용
// @Rollback(true) // 모든 테스트 종료 후 롤백
public class ReplyMapperTests {
   /*
    * BoardMapper를 어떻게 불러올 수 있지? 
    * Spring이 실핼될 때 BoardMapper의 구현체(프록시 객체)를 만들어서 자동으로 넣어주기 때문
    * 즉, BoardMapper는 interface이고 interface는 new BoardMapper()가 불가능 -> MyBatis가 구현체를 대신 만들어 줌
    */ 
   @Autowired
   private ReplyMapper mapper;
   
   // JUnit 테스트 코드라고 해도, 실제 DB에 반영됨
   @Test
   public void testGetList() {
	   int bno = 1;
      List<ReplyVO> list = mapper.getList(bno);
      for(ReplyVO vo : list) {
         log.info(vo);
      }
      // mapper.getList().forEach(vo -> log.info(vo)); // 최적화
   }
   
   @Test
   public void testInsert() {
	   ReplyVO vo = new ReplyVO();
      vo.setBno(32);
      vo.setReply("테스트 댓글");
      vo.setReplyer("tester");

       int result = mapper.insert(vo);
       log.info("insert result: " + result);
   }

   @Test
   public void testRead() {
	   ReplyVO vo = mapper.read(23); // bno=1
      log.info(vo);
   }
   
   @Test
   public void testDelete() {
      int result = mapper.delete(23); // bno=7
      log.info("delete count: " + result);
   }
   
   @Test
   public void testUpdate() {
	   ReplyVO vo = new ReplyVO();
      vo.setRno(23);
      vo.setReply("수정된 댓글 입니다");
      
      int result = mapper.update(vo);
      log.info("update result: " + result);
   }
   
}