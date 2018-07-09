package com.wpp.domain;

import java.util.Date;

public class Message {
	private int mnum; //�޽��� ��ȣ
	private String senduser;	//������
	private String receiver;	//�޴���
	private String content;	//�޽��� ����
	private Date date;	//�޽��� ��¥
	
	
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
