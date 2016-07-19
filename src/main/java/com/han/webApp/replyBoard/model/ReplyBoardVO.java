package com.han.webApp.replyBoard.model;

public class ReplyBoardVO {
	private int num;
	private String username;
	private String subject;
	private String content;
	private int hit;
	private String writedate;
	private String userip;
	private int ref;
	private int lev;
	private int step;
	
	private int cnt;	//결과값 처리 변수, 이렇게함으로써 메서드에 리턴형을 만들지 않아도됨. (결과값을 cnt에 담고 cnt로 결과처리)
	
	public ReplyBoardVO(){}
	public ReplyBoardVO(String username, String subject, String content, String userip){
		this.username = username;
		this.subject = subject;
		this.content = content;
		this.userip = userip;
	}
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
}
