package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.mapper.BoardMapper;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<ReplyVO> getList(int bno) {
		log.info("getList...");
		return mapper.getList(bno);
	}

	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("register..." + vo);
		
		int result = mapper.insert(vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);
	
		return result;
	}

	@Override
	public ReplyVO get(int rno) {
		log.info("get..." + rno);
		return mapper.read(rno);
	}

	@Transactional
	@Override
	public boolean remove(int rno) {
		log.info("remove..." + rno);
		
		 int bno =  mapper.getBno(rno);// 행 하나를 삭제 결과 + bno는 PK이기에
		 boolean result = mapper.delete(rno) == 1; 
		 
		 if (result) { // 삭제 성공 시만 감소
		        boardMapper.updateReplyCnt(bno, -1);  // 3️⃣ 댓글 수 -1
		    }
		 
		 return result;
	}

	@Override
	public boolean modify(ReplyVO vo) {
		log.info("modify..." + vo);
		return mapper.update(vo) == 1;
	}
	
	
}
