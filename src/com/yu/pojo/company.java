package com.yu.pojo;
//公司账户bean
public class company {
    private int c_id; //公司id
    private String c_account;//公司账户
    private String c_name;//公司名称
    private String c_explain;//说明
    public company() {
    }

    public company(int c_id, String c_account, String c_name) {
        this.c_id = c_id;
        this.c_account = c_account;
        this.c_name = c_name;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_account() {
        return c_account;
    }

    public void setC_account(String c_account) {
        this.c_account = c_account;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_explain() {
        return c_explain;
    }

    public void setC_explain(String c_explain) {
        this.c_explain = c_explain;
    }
}
