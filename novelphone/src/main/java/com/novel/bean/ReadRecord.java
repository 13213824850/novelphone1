package com.novel.bean;

import java.util.Date;

public class ReadRecord {
    private Integer id;

    private Integer novelid;

    private Integer contentid;

    private Integer userid;

    private Integer isupdate;

    private Date createtime;

    private Date updatetime;

    
    public ReadRecord() {
		// TODO Auto-generated constructor stub
	}
    
    public ReadRecord(Integer novelid, Integer contentid, Integer userid, Integer isupdate) {
		super();
		this.novelid = novelid;
		this.contentid = contentid;
		this.userid = userid;
		this.isupdate = isupdate;
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

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(Integer isupdate) {
        this.isupdate = isupdate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}