package com.yu.pojo;


//在管理系统上的每一次搜索都需要扣费
public class Cxkfjl {
    private int id;
    private String userid;
    private String companyname;
    private String skdate;
    private int jine;

    public Cxkfjl() {
    }

    public Cxkfjl(int id,String userid, String companyname, String skdate, int jine) {
        this.id = id;
        this.userid = userid;
        this.companyname = companyname;
        this.skdate = skdate;
        this.jine = jine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getSkdate() {
        return skdate;
    }

    public void setSkdate(String skdate) {
        this.skdate = skdate;
    }

    public int getJine() {
        return jine;
    }

    public void setJine(int jine) {
        this.jine = jine;
    }
}
