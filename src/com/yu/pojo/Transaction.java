package com.yu.pojo;

import java.util.Date;

//交易记录bean
public class Transaction {
    private int id;//交易记录编号
    private int c_id;//公司编号
    private int e_id;//雇主编号
    private int w_id;//工人编号
    private int jbr_id;//经办人编号
    private Date inputdate;//登记日期
    private Date overdate;//有效日期
    private String status;//状态
    private int price;//工资
    private String type;//雇佣类型
    private int charge;//中介费
    private Date accdate;//成交日期

    public Transaction() {
    }

    public Transaction(int id, int c_id, int e_id, int w_id, int jbr_id) {
        this.id = id;
        this.c_id = c_id;
        this.e_id = e_id;
        this.w_id = w_id;
        this.jbr_id = jbr_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public int getJbr_id() {
        return jbr_id;
    }

    public void setJbr_id(int jbr_id) {
        this.jbr_id = jbr_id;
    }

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    public Date getOverdate() {
        return overdate;
    }

    public void setOverdate(Date overdate) {
        this.overdate = overdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public Date getAccdate() {
        return accdate;
    }

    public void setAccdate(Date accdate) {
        this.accdate = accdate;
    }
}
