package com.han.webApp.replyBoard.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardViewCommand implements ReplyBoardServiceInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		ReplyBoardVO vo = dao.select(num);  //레코드선택
		//조회수증가
		dao.hitCount(num);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("replyBoard/replyView");
		return mav;
	}

}
