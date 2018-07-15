package com.wpp.domain;

import java.util.Date;

public class Message {
	private int mnum; //메시지 번호
	private String senduser;	//보낸이
	private String receiver;	//받는이
	private String content;	//메시지 내용
	private Date date;	//메시지 날짜
	
	
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getSenduser() {
		return senduser;
	}
	public void setSenduser(String senduser) {
		this.senduser = senduser;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "Message [mnum=" + mnum + ", senduser=" + senduser + ", receiver=" + receiver + ", content=" + content
				+ ", date=" + date + "]";
	}
	
	
}
