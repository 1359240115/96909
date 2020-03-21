package com.yu.service.serviceImp;

import com.yu.dao.Xtgl_Dao;
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
}
