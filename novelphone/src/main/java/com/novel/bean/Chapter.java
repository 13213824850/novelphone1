package com.novel.bean;

public class Chapter {
    private Integer id;

    private String chaptertext;

    private Integer contentid;

    private String createtime;

    private Integer count;

    private String source;

    private Integer novelid;

    private Integer num;
    public Chapter() {
		// TODO Auto-generated constructor stub
	}

    public Chapter(Integer id, String chaptertext, Integer contentid, String createtime, Integer count, String source,
			Integer novelid, Integer num) {
		super();
		this.id = id;
		this.chaptertext = chaptertext;
		this.contentid = contentid;
		this.createtime = createtime;
		this.count = count;
		this.source = source;
		this.novelid = novelid;
		this.num = num;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChaptertext() {
        return chaptertext;
    }

    public void setChaptertext(String chaptertext) {
        this.chaptertext = chaptertext == null ? null : chaptertext.trim();
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getNovelid() {
        return novelid;
    }

    public void setNovelid(Integer novelid) {
        this.novelid = novelid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}