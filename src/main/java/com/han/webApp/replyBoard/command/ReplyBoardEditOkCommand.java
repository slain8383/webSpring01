package com.han.webApp.replyBoard.command;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardEditOkCommand {
	public ModelAndView execute(ReplyBoardVO vo){
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		int cnt = dao.update(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", cnt);
		mav.addObject("num", vo.getNum());
		mav.setViewName("replyBoard/replyEditOk");
		
		return mav;
	}
}
