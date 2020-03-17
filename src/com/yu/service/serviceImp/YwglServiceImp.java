package com.yu.service.serviceImp;

import com.yu.dao.Ywgl_Dao;
import com.yu.pojo.Employer;
import com.yu.pojo.Worker;
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
    public boolean addEmployer(Employer employer,String account) {
        return dao.addEmployer(employer,account);
    }
}