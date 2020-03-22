package com.yu.service;

import com.yu.pojo.Cxkfjl;
import com.yu.pojo.Czjl;
import com.yu.pojo.User;

import java.util.List;

public interface XtglService {

    List<User> showCzglMain();

    boolean accountRecharge(Czjl czjl, int accountid);

    List<User> queryYueByJs(String companyname,String accountid);

    List<Cxkfjl> showAllKfjl();

    List<Cxkfjl> jsKfjl(String companyname,String accountid,String mindate,String maxdate);

    boolean koufeiByJs(String userid);

}
