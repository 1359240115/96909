package com.yu.service.serviceImp;

import com.yu.dao.Xtgl_Dao;
import com.yu.pojo.Cxkfjl;
import com.yu.pojo.Czjl;
import com.yu.pojo.User;
import com.yu.service.XtglService;

import java.util.List;

public class XtglServiceImp implements XtglService {
    Xtgl_Dao dao = new Xtgl_Dao();
    @Override
    public List<User> showCzglMain() {
        return dao.showCzglMain();
    }

    @Override
    public boolean accountRecharge(Czjl czjl, int accountid) {
        return dao.accountRecharge(czjl,accountid);
    }

    @Override
    public List<User> queryYueByJs(String companyname, String accountid) {
        return dao.queryYueByJs(companyname,accountid);
    }

    @Override
    public List<Cxkfjl> showAllKfjl() {
        return dao.queryAllKfjl();
    }

    @Override
    public List<Cxkfjl> jsKfjl(String companyname,String accountid,String mindate,String maxdate) {
        return dao.jsKfjl(companyname,accountid,mindate,maxdate);
    }

    @Override
    public boolean koufeiByJs(String userid) {
        return dao.koufeiByJs(userid);
    }
}
