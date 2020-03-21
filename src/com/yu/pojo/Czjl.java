package com.yu.pojo;


//充值记录的javabean
public class Czjl {
    private int id;//充值记录编号
    private int cid;//给哪个公司充值
    private int jine;//充值金额
    private String chargeman;//操作人
    private String inputdate;//充值时间
    private String beizhu;//备注
    private String czname;//给谁的账户充值的

    public Czjl() {
    }

    public Czjl(int id, int cid, int jine, String chargeman, String inputdate, String beizhu) {
        this.id = id;
        this.cid = cid;
        this.jine = jine;
        this.chargeman = chargeman;
        this.inputdate = inputdate;
        this.beizhu = beizhu;
    }

    public String getCzname() {
        return czname;
    }

    public void setCzname(String czname) {
        this.czname = czname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getJine() {
        return jine;
    }

    public void setJine(int jine) {
        this.jine = jine;
    }

    public String getChargeman() {
        return chargeman;
    }

    public void setChargeman(String chargeman) {
        this.chargeman = chargeman;
    }

    public String getInputdate() {
        return inputdate;
    }

    public void setInputdate(String inputdate) {
        this.inputdate = inputdate;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
