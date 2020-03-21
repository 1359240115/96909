package com.yu.service;

import com.yu.pojo.Czjl;
import com.yu.pojo.User;

import java.util.List;

public interface XtglService {

    List<User> showCzglMain();

    boolean accountRecharge(Czjl czjl, int accountid);
}
