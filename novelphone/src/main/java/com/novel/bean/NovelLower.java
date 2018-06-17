package com.novel.bean;

import java.util.Date;

public class NovelLower {
    private Integer novelid;

    private Integer islower;

    private Date createtime;

    private Date updatetime;

    public Integer getNovelid() {
        return novelid;
    }

    public void setNovelid(Integer novelid) {
        this.novelid = novelid;
    }

    public Integer getIslower() {
        return islower;
    }

    public void setIslower(Integer islower) {
        this.islower = islower;
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