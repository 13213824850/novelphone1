package com.novel.bean;

import java.util.Date;

public class Novel {
    private Integer id;

    private String title;

    private String auth;

    private String type;

    private String titleurl;

    private String newtime;

    private String newchapter;

    private Integer newchapterurl;

    private String image;

    private String body;

    private Integer contentid;

    private String state;

    private String source;

    private String product;

    private Integer productid;

    private Integer isupdate;

    private Date createtime;

    private Date updatetime;
    
    public Novel() {
		// TODO Auto-generated constructor stub
	}
    

    public Novel(int id,String title, String auth, String type, String titleurl, String newtime, String newchapter,
			Integer newchapterurl, String image, String body, Integer contentid, String state, String source,
			String product, Integer productid, Date createtime, Date updatetime) {
		super();
		this.id=id;
		this.title = title;
		this.auth = auth;
		this.type = type;
		this.titleurl = titleurl;
		this.newtime = newtime;
		this.newchapter = newchapter;
		this.newchapterurl = newchapterurl;
		this.image = image;
		this.body = body;
		this.contentid = contentid;
		this.state = state;
		this.source = source;
		this.product = product;
		this.productid = productid;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth == null ? null : auth.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitleurl() {
        return titleurl;
    }

    public void setTitleurl(String titleurl) {
        this.titleurl = titleurl == null ? null : titleurl.trim();
    }

    public String getNewtime() {
        return newtime;
    }

    public void setNewtime(String newtime) {
        this.newtime = newtime == null ? null : newtime.trim();
    }

    public String getNewchapter() {
        return newchapter;
    }

    public void setNewchapter(String newchapter) {
        this.newchapter = newchapter == null ? null : newchapter.trim();
    }

    public Integer getNewchapterurl() {
        return newchapterurl;
    }

    public void setNewchapterurl(Integer newchapterurl) {
        this.newchapterurl = newchapterurl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
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