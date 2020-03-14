package com.yu.service.serviceImp;

import com.yu.dao.LoginDao;
import com.yu.pojo.User;
import com.yu.service.LoginCheckService;

public class LoginCheckImp implements LoginCheckService {
    LoginDao loginDao = new LoginDao();

    //检查登录用户的信息
    @Override
    public boolean CheckUser(User user) {
        return loginDao.checkUser(user);
    }
}
