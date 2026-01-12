package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;

public interface BoardAttachMapper{
	public void insert(BoardAttachVO vo);
	// 개별 파일 삭제
	public void delete(String uuid);
	public List<BoardAttachVO> findByBno(int bno);
	// 게시글 수정용
	public void deleteAll(int bno);
	
}