package com.yu.pojo;

public class NoticeMrtz {
    int id;
    String context;
    String noticeTime;

    public NoticeMrtz() {
    }

    public NoticeMrtz(int id, String context, String noticeTime) {
        this.id = id;
        this.context = context;
        this.noticeTime = noticeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }
}
