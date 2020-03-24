package com.yu.service.serviceImp;

import com.yu.dao.Ywgl_Dao;
import com.yu.pojo.*;
import com.yu.service.YwglService;

import java.util.List;

public class YwglServiceImp implements YwglService {
    Ywgl_Dao dao = new Ywgl_Dao();
    @Override
    public List<Worker> queryWokerByJS(Worker worker,int minage,int maxage) {
        return dao.JiansuoWoker(worker,minage,maxage);
    }

    @Override
    public List<Employer> queryEmployer() {
        return dao.queryAllEmployer();
    }

    @Override
    public List<Employer> queryEmployerByJS(Employer employer) {
        return dao.queryEmployerByJS(employer);
    }

    @Override
    public boolean addEmployer(Employer employer) {
        return dao.addEmployer(employer);
    }

    @Override
    public Employer seeEmployerByid(String id) {
        return dao.seeEmployerByid(id);
    }

    @Override
    public boolean updateEmployerByhetonghao(Employer employer) {
        return dao.updateEmployerByhetonghao(employer);
    }

    @Override
    public List<NoticeMrtz> showAllNotice() {
        return dao.showAllNotice();
    }

    @Override
    public List<MessageBean> messageList(String jieshouren) {
        return dao.queryAllMessage(jieshouren);
    }

    @Override
    public MessageBean showMessageByMid(int mid) {
        return dao.queryMessageByMid(mid);
    }

    @Override
    public Boolean addMessage(MessageBean messageBean) {
        return dao.addMessage(messageBean);
    }

    @Override
    public List<User> findAllUser() {
        return dao.findUsers();
    }

    @Override
    public List<String> findjsrBycname(String cname) {
        return dao.findjsrBycname(cname);
    }

    @Override
    public List<Transaction> showAlltransaction() {
        return dao.showAllTransaction();
    }

    @Override
    public List<Transaction> queryTransactionByJs(Transaction t, String mintime, String maxtime) {
        return dao.queryTransactionByJs(t,mintime,maxtime);
    }

    @Override
    public boolean addTransaction(Transaction t) {
        return dao.addTransaction(t);
    }

    @Override
    public List<String> allW() {
        return dao.allW();
    }

    public List<String> allE(){
        return dao.allE();
    }
}