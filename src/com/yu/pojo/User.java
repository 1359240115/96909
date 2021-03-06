package com.yu.pojo;

//登录的对象
public class User {
    private int accountid;//公司账号
    private int userid;//用户id
    private String name; //账号拥有者的名字
    private String email;//账号拥有者的邮箱
    private String password;//密码
    private String status;//状态
    private int type;//公司类型
    private int companyid;//账号所属公司
    private String companyname;//公司名称
    private int yue;//账户余额
    private int skcs;//刷卡次数
    private int sfbz;//收费标准
    private String bz;//备注

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public User() {
    }

    public User(int userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public int getYue() {
        return yue;
    }

    public void setYue(int yue) {
        this.yue = yue;
    }

    public int getSkcs() {
        return skcs;
    }

    public void setSkcs(int skcs) {
        this.skcs = skcs;
    }

    public int getSfbz() {
        return sfbz;
    }

    public void setSfbz(int sfbz) {
        this.sfbz = sfbz;
    }
}
