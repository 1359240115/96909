package com.yu.pojo;

import java.util.Date;

//表示雇主
public class employer {
    private int id;
    private int c_id;
    private String name;
    private String sex;
    private int age;
    private String mingzu;
    private String jiguan;
    private String xueli;
    private String idcard;
    private String zhiye;
    private String hetonghao;
    private Date hetongqixian;
    private int phone;
    private String address;
    private String yaoqiu;
    private int jingbanren;
    private Date inputdate;
    private float maxprice;
    private float minprice;
    private Date birthday;

    public employer() {
    }

    public employer(int id, int c_id) {
        this.id = id;
        this.c_id = c_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMingzu() {
        return mingzu;
    }

    public void setMingzu(String mingzu) {
        this.mingzu = mingzu;
    }

    public String getJiguan() {
        return jiguan;
    }

    public void setJiguan(String jiguan) {
        this.jiguan = jiguan;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getZhiye() {
        return zhiye;
    }

    public void setZhiye(String zhiye) {
        this.zhiye = zhiye;
    }

    public String getHetonghao() {
        return hetonghao;
    }

    public void setHetonghao(String hetonghao) {
        this.hetonghao = hetonghao;
    }

    public Date getHetongqixian() {
        return hetongqixian;
    }

    public void setHetongqixian(Date hetongqixian) {
        this.hetongqixian = hetongqixian;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYaoqiu() {
        return yaoqiu;
    }

    public void setYaoqiu(String yaoqiu) {
        this.yaoqiu = yaoqiu;
    }

    public int getJingbanren() {
        return jingbanren;
    }

    public void setJingbanren(int jingbanren) {
        this.jingbanren = jingbanren;
    }

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    public float getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(float maxprice) {
        this.maxprice = maxprice;
    }

    public float getMinprice() {
        return minprice;
    }

    public void setMinprice(float minprice) {
        this.minprice = minprice;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
