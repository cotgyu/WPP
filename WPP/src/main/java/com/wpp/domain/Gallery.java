package com.wpp.domain;

import java.util.Date;

public class Gallery {
	Integer imgid;	//�̹��� ��ȣ
	String imgname;	//�̹��� ����
	String imgfile; //���� �����̸�
	Date imgdate;	//�ۼ���¥
	Integer imgup; //�̹��� ��õ ��
	String imgwriter; //�ۼ���
	
	public String getImgwriter() {
		return imgwriter;
	}
	public void setImgwriter(String imgwriter) {
		this.imgwriter = imgwriter;
	}
	public Integer getImgid() {
		return imgid;
	}
	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getImgfile() {
		return imgfile;
	}
	public void setImgfile(String imgfile) {
		this.imgfile = imgfile;
	}
	public Date getImgdate() {
		return imgdate;
	}
	public void setImgdate(Date imgdate) {
		this.imgdate = imgdate;
	}
	public Integer getImgup() {
		return imgup;
	}
	public void setImgup(Integer imgup) {
		this.imgup = imgup;
	}
	
	@Override
	public String toString() {
		return "Gallery [imgid=" + imgid + ", imgname=" + imgname + ", imgfile=" + imgfile + ", imgdate=" + imgdate
				+ ", imgup=" + imgup + ", imgwriter=" + imgwriter + "]";
	}

	
}
