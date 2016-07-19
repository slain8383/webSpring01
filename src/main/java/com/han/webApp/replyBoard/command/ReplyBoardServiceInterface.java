package com.han.webApp.replyBoard.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface ReplyBoardServiceInterface {
	public ModelAndView execute(HttpServletRequest request);
}
