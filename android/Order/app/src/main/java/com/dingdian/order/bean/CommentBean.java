package com.dingdian.order.bean;

public class CommentBean {

    /**
     * id : 17
     * orderid : 201902202309177424555753050
     * content : 1
     * core : 10
     * schoolId : 9
     * createTime : 2019-02-20 23:19:08.0
     */

    private int id;
    private String orderid;
    private String content;
    private String core;
    private int schoolId;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
