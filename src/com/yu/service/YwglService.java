package com.yu.service;

import com.yu.pojo.*;

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

    List<User> findAllUser();

    List<String> findjsrBycname(String cname);

    List<Transaction> showAlltransaction();

    List<Transaction> queryTransactionByJs(Transaction t ,String mintime , String maxtime);

    boolean addTransaction(Transaction t);

    List<String> allW();

    List<String> allE();
}
