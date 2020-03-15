package com.yu.pojo;
import java.text.DateFormat;
import java.util.Date;

//工人bean
public class Worker {
    private int w_id;   //员工编号
    private int c_id;   //公司编号
    private String name;//名字
    private String sex;//性别
    private String xueli;//学历
    private String idcard;//身份证号
    private Date birthday;//出生日期
    private int age;//年龄
    private int tel;//手机
    private int phone;//电话
    private String type;//工种
    private String  pic;//相片
    private String paddress;//户籍地址
    private String address;//地址
    private String banknumber;//银行卡号
    private String[] yuyan;//语言
    private String status;//状态
    private String hunfou;//婚否
    private String[] jineng;//技能
    private Date inputtime;//录入时间
    private int company;//所属公司

    public Worker() {
    }

    public Worker(int w_id, int c_id, String name, String sex) {
        this.w_id = w_id;
        this.c_id = c_id;
        this.name = name;
        this.sex = sex;
    }

    public String[] getYuyan() {
        return yuyan;
    }

    public void setYuyan(String[] yuyan) {
        this.yuyan = yuyan;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBanknumber() {
        return banknumber;
    }

    public void setBanknumber(String banknumber) {
        this.banknumber = banknumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHunfou() {
        return hunfou;
    }

    public void setHunfou(String hunfou) {
        this.hunfou = hunfou;
    }

    public String[] getJineng() {
        return jineng;
    }

    public void setJineng(String[] jineng) {
        this.jineng = jineng;
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }
}
