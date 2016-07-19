package com.han.webApp.replyBoard.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardWriteOkCommand implements ReplyBoardServiceInterface {

	@Override
	public ModelAndView execute(HttpServletRequest r) {
		
		ReplyBoardVO vo = new ReplyBoardVO(r.getParameter("username"),r.getParameter("subject"),
										   r.getParameter("content"),r.getRemoteAddr()); //getRemoteAddr:ip���ϴ� �޼���
		
		
		ReplyBoardDAO dao = new ReplyBoardDAO();
		int cnt = dao.replyWrite(vo);	//dao���� DB�۾��ϰ� vo�� �����͸� ��� ����

		////
		ModelAndView mav = new ModelAndView();
		mav.addObject("cnt", cnt); //executeUpdate�� ���� 0 or 1���� cnt�Τ�
		mav.setViewName("replyBoard/replyFormOk");
		
		return mav;
	}

}
