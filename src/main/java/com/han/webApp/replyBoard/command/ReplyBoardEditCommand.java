package com.han.webApp.replyBoard.command;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardEditCommand {
	public ModelAndView execute(int num){
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		ReplyBoardVO vo = dao.select(num);
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("replyBoard/replyEdit");
		return mav;
	}
}
