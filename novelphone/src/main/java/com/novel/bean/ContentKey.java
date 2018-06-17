package com.novel.bean;

public class ContentKey {
    private Integer id;

    private Integer novelid;
    
    
public ContentKey() {
	// TODO Auto-generated constructor stub
}
    public ContentKey(Integer id, Integer novelid) {
		super();
		this.id = id;
		this.novelid = novelid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNovelid() {
        return novelid;
    }

    public void setNovelid(Integer novelid) {
        this.novelid = novelid;
    }
}