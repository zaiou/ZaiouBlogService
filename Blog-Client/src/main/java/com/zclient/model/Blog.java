package com.zclient.model;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {

    private static final long serialVersionUID = -6679108303502417899L;

    private Integer blogid;

    private String title;

    private String summary;

    private Date createTime;

    private Integer categoryid;

    private Integer isHotspot;

    private String imageurl;

    private String content;

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getIsHotspot() {
        return isHotspot;
    }

    public void setIsHotspot(Integer isHotspot) {
        this.isHotspot = isHotspot;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}