package com.yu.pojo;

import java.text.SimpleDateFormat;

public class MessageBean {
    private int id;
    private int mid;
    private String title;
    private String fasongren;
    private String jieshouren;
    private String fssj;
    private String context;
    private String status;

    public MessageBean() {

    }

    public MessageBean(int mid, String title, String fasongren, String status, String fssj) {
        this.mid = mid;
        this.title = title;
        this.fasongren = fasongren;
        this.status = status;
        this.fssj = fssj;
    }

    public MessageBean(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFasongren() {
        return fasongren;
    }

    public void setFasongren(String fasongren) {
        this.fasongren = fasongren;
    }

    public String getJieshouren() {
        return jieshouren;
    }

    public void setJieshouren(String jieshouren) {
        this.jieshouren = jieshouren;
    }

    public String getFssj() {
        return fssj;
    }

    public void setFssj(String fssj) {
        this.fssj = fssj;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
