package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.mapper.BoardAttachMapper;
import org.joonzis.mapper.BoardMapper;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper mapper;
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private BoardAttachMapper attachMapper;
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList..." + cri);
		return mapper.getList(cri);
	}
	
	@Override
	public int getTotal() {
		log.info("getTotal...");
		return mapper.getTotal();
	}
//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList...");
//		return mapper.getList();
//	}

	@Transactional
	@Override
	public void register(BoardVO vo) {
		log.info("register..." + vo);
		
		// 1. 게시글 등록
		mapper.insert(vo);
		
		// 2. 등록된 게시글의 번호 가져온다
		int bno = mapper.getCurrBno();
		
		
		// 3. 해당 게시글 번호로 첨부 파일 등록
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			
			vo.getAttachList().forEach(attach -> {
				attach.setBno(bno);
				attachMapper.insert(attach);
			});
			// 반복해서  아래 메소드가 실행되면 하면됨
			//  왜 반복? vo.getAttachList()가 List 니까
			// insert 메소드에 전달하는 vo는 BoardAttachVO
			// BoardAttachVO의 구조를 잘 보고
			// 잘 전달해서 잘 입력해보기
		}
		
	}

	@Override
	public BoardVO get(int bno) {
		log.info("get..." + bno);
		return mapper.read(bno);
	}

	// 댓글 포함 게시글 삭제
	@Transactional
	@Override
	public boolean remove(int bno) {
		log.info("remove..." + bno);
		replyMapper.deleteByBno(bno); // 댓글 먼저 삭제 (fk 해결)
		return mapper.delete(bno) == 1; // 행 하나를 삭제 결과 + bno는 PK이기에
	}

	@Override
	public boolean modify(BoardVO vo) {
		log.info("modify..." + vo);
		return mapper.update(vo) == 1;
	}
	
	

}
