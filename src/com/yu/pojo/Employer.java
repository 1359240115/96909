package com.yu.pojo;

import java.util.Date;

//表示雇主
public class Employer {
    private int id;
    private int c_id;
    private String name;
    private String sex;
    private int age;
    private String mingzu;
    private String jiguan;
    private String xueli;
    private int idcard;
    private String gzdw;//工作单位
    private String zhiye;
    private String hetonghao;
    private Date hetongqixian;
    private Integer phone;
    private String address;
    private String yaoqiu;//工人类型
    private int jingbanren;
    private Date inputdate;
    private float maxprice;
    private float minprice;
    private Date birthday;
    private String status;
    private String workspace;
    private int jtrs;//家庭人数
    private int fwmj;
    private String fwnr;//服务内容
    private String ysxg;
    private String qita;//家庭内容


    public Employer(int id, int c_id, String name, String sex, int age, String mingzu, String jiguan, String xueli, int idcard, String gzdw, String zhiye, String hetonghao, Date hetongqixian, Integer phone, String address, String yaoqiu, int jingbanren, Date inputdate, float maxprice, float minprice, Date birthday, String status, String workspace, int jtrs, int fwmj, String fwnr, String ysxg, String qita) {
        this.id = id;
        this.c_id = c_id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.mingzu = mingzu;
        this.jiguan = jiguan;
        this.xueli = xueli;
        this.idcard = idcard;
        this.gzdw = gzdw;
        this.zhiye = zhiye;
        this.hetonghao = hetonghao;
        this.hetongqixian = hetongqixian;
        this.phone = phone;
        this.address = address;
        this.yaoqiu = yaoqiu;
        this.jingbanren = jingbanren;
        this.inputdate = inputdate;
        this.maxprice = maxprice;
        this.minprice = minprice;
        this.birthday = birthday;
        this.status = status;
        this.workspace = workspace;
        this.jtrs = jtrs;
        this.fwmj = fwmj;
        this.fwnr = fwnr;
        this.ysxg = ysxg;
        this.qita = qita;
    }

    public Employer() {
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public int getJtrs() {
        return jtrs;
    }

    public void setJtrs(int jtrs) {
        this.jtrs = jtrs;
    }

    public int getFwmj() {
        return fwmj;
    }

    public void setFwmj(int fwmj) {
        this.fwmj = fwmj;
    }

    public String getFwnr() {
        return fwnr;
    }

    public void setFwnr(String fwnr) {
        this.fwnr = fwnr;
    }

    public String getYsxg() {
        return ysxg;
    }

    public void setYsxg(String ysxg) {
        this.ysxg = ysxg;
    }

    public String getQita() {
        return qita;
    }

    public void setQita(String qita) {
        this.qita = qita;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getIdcard() {
        return idcard;
    }

    public void setIdcard(int idcard) {
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
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
