package com.novel.bean;

public class Content extends ContentKey {
    private String chaptertext;

    private Integer preid;

    private Integer nextid;

    private Integer num;

    private String content;
    
    public Content() {
		// TODO Auto-generated constructor stub
	}

    public Content(String chaptertext, Integer preid, Integer nextid, Integer num, String content) {
		super();
		this.chaptertext = chaptertext;
		this.preid = preid;
		this.nextid = nextid;
		this.num = num;
		this.content = content;
	}

	public String getChaptertext() {
        return chaptertext;
    }

    public void setChaptertext(String chaptertext) {
        this.chaptertext = chaptertext == null ? null : chaptertext.trim();
    }

    public Integer getPreid() {
        return preid;
    }

    public void setPreid(Integer preid) {
        this.preid = preid;
    }

    public Integer getNextid() {
        return nextid;
    }

    public void setNextid(Integer nextid) {
        this.nextid = nextid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}