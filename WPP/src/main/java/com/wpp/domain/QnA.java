package com.wpp.domain;

import java.util.Date;

public class QnA {
	private int bnum;
	private String title;
	private String content;
	private String writer;
	private Date date;
	private int hit;
	private int recnt;
	private String boardtag; 
	private String answer;
	private int qgroup;
	
	public String getBoardtag() {
		return boardtag;
	}
	public void setBoardtag(String boardtag) {
		this.boardtag = boardtag;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRecnt() {
		return recnt;
	}

	public void setRecnt(int recnt) {
		this.recnt = recnt;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	public int getQgroup() {
		return qgroup;
	}
	public void setQgroup(int qgroup) {
		this.qgroup = qgroup;
	}
	@Override
	public String toString() {
		return "QnA [bnum=" + bnum + ", title=" + title + ", content=" + content + ", writer=" + writer + ", date="
				+ date + ", hit=" + hit + ", recnt=" + recnt + ", boardtag="+ boardtag+ ", answer="+answer+", qgroup="+qgroup+"]";
	}
	
	

}