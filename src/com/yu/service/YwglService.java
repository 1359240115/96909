package com.yu.service;

import com.yu.pojo.Employer;
import com.yu.pojo.Worker;

import java.util.List;


public interface YwglService {
    List<Worker> queryWokerByJS(Worker worker, int minage, int maxage);

    List<Employer> queryEmployer();

    List<Employer> queryEmployerByJS(Employer employer);
}
