package com.wpp.domain;

import java.util.Date;

public class FreeBoard {
	private int bnum;		 //글번호
	private String title; 	//글제목
 	private String content;	//글내용
	private String writer;	//작성자
	private Date date;		//작성날짜
	private int hit;		//조회수
	private int recnt; 		//댓글 수
	private String tag;		//자유게시판 태그
	private String boardtag; //최신글 구분을 위한 글 종류 
	
	public String getBoardtag() {
		return boardtag;
	}
	public void setBoardtag(String boardtag) {
		this.boardtag = boardtag;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	
	@Override
	public String toString() {
		return "FreeBoard [bnum=" + bnum + ", title=" + title + ", content=" + content + ", writer=" + writer + ", date="
				+ date + ", hit=" + hit + ",  recnt=" + recnt + ", tag=" + tag + ", boardtag="+ boardtag+ " ]";
	}
	
	

}