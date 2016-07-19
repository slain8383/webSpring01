package com.han.webApp.replyBoard.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardListCommand implements ReplyBoardServiceInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		
		//레코드 선택
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		List<ReplyBoardVO> list = dao.replyList();
		
		//총 레코드 수
		int totalRecord = dao.getTotalRecored();
		
		//총레코드수 - ((현재페이지-1)*한페이지 출력레코드수) --> 시작페이지 번호
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("startNumber", totalRecord);
		mav.setViewName("replyBoard/replyList");	//view페이지 이름 셋팅
		return mav;
	}

}
