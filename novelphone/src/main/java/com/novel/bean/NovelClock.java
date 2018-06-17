package com.novel.bean;

import java.util.Date;

public class NovelClock {
    private Integer id;

    private String chapterurl;

    private Integer clockstate;

    private String titleurl;

    private String state;

    private Date createtime;

    private Date updatetime;

    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapterurl() {
        return chapterurl;
    }

    public void setChapterurl(String chapterurl) {
        this.chapterurl = chapterurl == null ? null : chapterurl.trim();
    }

    public Integer getClockstate() {
        return clockstate;
    }

    public void setClockstate(Integer clockstate) {
        this.clockstate = clockstate;
    }

    public String getTitleurl() {
        return titleurl;
    }

    public void setTitleurl(String titleurl) {
        this.titleurl = titleurl == null ? null : titleurl.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}