package com.han.webApp.replyBoard.model;

import java.util.List;

public interface ReplyBoardInterface {
	
	//글목록
	public List<ReplyBoardVO> replyList();
	
	//글등록
	public int replyWrite(ReplyBoardVO vo);
	
	//글내용
	public ReplyBoardVO select(int num);
	
	//글수정
	//답글달기
	//삭제
	
	//총레코드수
	public int getTotalRecored();

	public int update(ReplyBoardVO vo);
}
