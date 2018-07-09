package com.wpp.domain;

import java.util.Date;

public class WebReply {
	private Integer rnum;       
    private Integer bnum;        
    private String replytext;    
    private String replyer;        
    private Date date;       
    private int regroup;
	private int restep;
	private int reindent;
    private String replytag; 
  

	public String getReplytag() {
		return replytag;
	}
	public void setReplytag(String replytag) {
		this.replytag = replytag;
	}
	public int getRegroup() {
		return regroup;
	}
	public void setRegroup(int regroup) {
		this.regroup = regroup;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getReindent() {
		return reindent;
	}
	public void setReindent(int reindent) {
		this.reindent = reindent;
	}
	// Getter/Setter
    public Integer getRnum() {
        return rnum;
    }
    public void setRnum(Integer rnum) {
        this.rnum = rnum;
    }
    public Integer getBnum() {
        return bnum;
    }
    public void setBnum(Integer bnum) {
        this.bnum = bnum;
    }
    public String getReplytext() {
        return replytext;
    }
    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }
    public String getReplyer() {
        return replyer;
    }
    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }
    public Date getdate() {
        return date;
    }
    public void setdate(Date date) {
        this.date = date;
    }

    
    // toString()
    @Override
    public String toString() {
        return "WebReply [rnum=" + rnum + ", bnum=" + bnum + ", replytext=" + replytext + ", replyer=" + replyer
                +  ", date=" + date + ", regroup=" + regroup +", restep=" +restep+", reindent="+ reindent+"]";
    }
    
}