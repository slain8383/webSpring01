package com.han.webApp.replyBoard.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.model.ReplyBoardDAO;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

public class ReplyBoardListCommand implements ReplyBoardServiceInterface {

	@Override
	public ModelAndView execute(HttpServletRequest request) {
		
		//���ڵ� ����
		ReplyBoardDAO dao = ReplyBoardDAO.getInstance();
		List<ReplyBoardVO> list = dao.replyList();
		
		//�� ���ڵ� ��
		int totalRecord = dao.getTotalRecored();
		
		//�ѷ��ڵ�� - ((����������-1)*�������� ��·��ڵ��) --> ���������� ��ȣ
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("startNumber", totalRecord);
		mav.setViewName("replyBoard/replyList");	//view������ �̸� ����
		return mav;
	}

}
