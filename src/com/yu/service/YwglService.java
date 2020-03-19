package com.yu.service;

import com.yu.pojo.Employer;
import com.yu.pojo.MessageBean;
import com.yu.pojo.NoticeMrtz;
import com.yu.pojo.Worker;

import java.util.List;


public interface YwglService {
    List<Worker> queryWokerByJS(Worker worker, int minage, int maxage);

    List<Employer> queryEmployer();

    List<Employer> queryEmployerByJS(Employer employer);

    boolean addEmployer(Employer employer);

    Employer seeEmployerByid(String id);

    boolean updateEmployerByhetonghao(Employer employer);

    List<NoticeMrtz> showAllNotice();

    List<MessageBean> messageList(String jieshouren);

    MessageBean showMessageByMid(int mid);

    Boolean addMessage(MessageBean messageBean);
}
