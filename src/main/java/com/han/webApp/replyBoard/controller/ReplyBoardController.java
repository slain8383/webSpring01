package com.han.webApp.replyBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.han.webApp.replyBoard.command.ReplyBoardDelCommand;
import com.han.webApp.replyBoard.command.ReplyBoardEditCommand;
import com.han.webApp.replyBoard.command.ReplyBoardEditOkCommand;
import com.han.webApp.replyBoard.command.ReplyBoardListCommand;
import com.han.webApp.replyBoard.command.ReplyBoardRewriteOkCommand;
import com.han.webApp.replyBoard.command.ReplyBoardViewCommand;
import com.han.webApp.replyBoard.command.ReplyBoardWriteOkCommand;
import com.han.webApp.replyBoard.model.ReplyBoardVO;

@Controller
public class ReplyBoardController {

	public ReplyBoardController(){}
	
	//replyBoard List
	@RequestMapping("/replyBoard/replyList") //("view���ϰ��")
	public ModelAndView replyList(HttpServletRequest request){
		ReplyBoardListCommand com = new ReplyBoardListCommand();
		ModelAndView mav = com.execute(request);
		
		return mav;
	}
	//�۾�����
	@RequestMapping(value="/replyBoard/write", method=RequestMethod.GET)
	public String replyWtite(){
		return "replyBoard/replyForm";
	}
	
	//������
	@RequestMapping(value="/replyBoard/replyWriteOk", method=RequestMethod.POST)
	public ModelAndView replyWriteOk(HttpServletRequest r){
		ReplyBoardWriteOkCommand command = new ReplyBoardWriteOkCommand();
		return command.execute(r);
	}
	
	//�۳��� ����
	@RequestMapping(value="/replyBoard/View")
	public ModelAndView replyView(HttpServletRequest r){
		ReplyBoardViewCommand command = new ReplyBoardViewCommand();
		return command.execute(r);
	}
	//�ۼ��� ��
	@RequestMapping("/replyBoard/replyEdit")
	public ModelAndView replyEdit(@RequestParam("num") int num){
		ReplyBoardEditCommand command = new ReplyBoardEditCommand();
		
		return command.execute(num);
	}
	//�ۼ���
	@RequestMapping("/replyBoard/replyEditOk")
	public ModelAndView replyEditOk(ReplyBoardVO vo){
		ReplyBoardEditOkCommand command = new ReplyBoardEditOkCommand();
		return command.execute(vo);
	}
	//�亯�۾��� ��
	@RequestMapping("/replyBoard/replyWrite")
	public ModelAndView replyWrite(@RequestParam("num") int num){
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", num);
		mav.setViewName("replyBoard/replyWrite");
		return mav; 
	}
	//�亯�� ����
	@RequestMapping("/replyBoard/replyRewriteOk")
	public ModelAndView replyRewriteOk(HttpServletRequest r, ReplyBoardVO vo){
		ReplyBoardRewriteOkCommand command = new ReplyBoardRewriteOkCommand();
		vo.setUserip(r.getRemoteAddr());
		return command.execute(vo);
	}
	//�ۻ���
	@RequestMapping("/replyBoard/replyDel")
	public ModelAndView replyDel(@RequestParam("num") int num){
		ReplyBoardDelCommand command = new ReplyBoardDelCommand();	
		return command.execute(num);
	}
}
