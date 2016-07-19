package com.han.webApp.replyBoard.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardWriteOkCommand implements ReplyBoardServiceInterface {

	@Override
	public ModelAndView execute(HttpServletRequest r) {
		
		ReplyBoardVO vo = new ReplyBoardVO(r.getParameter("username"),r.getParameter("subject"),
										   r.getParameter("content"),r.getRemoteAddr()); //getRemoteAddr:ip구하는 메서드
		
		
		ReplyBoardDAO dao = new ReplyBoardDAO();
		int cnt = dao.replyWrite(vo);	//dao에서 DB작업하고 vo에 데이터를 담고 리턴

		////
		ModelAndView mav = new ModelAndView();
		mav.addObject("cnt", cnt); //executeUpdate에 의해 0 or 1값이 cnt로ㅡ
		mav.setViewName("replyBoard/replyFormOk");
		
		return mav;
	}

}
