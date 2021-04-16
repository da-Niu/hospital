package com.service;

import com.entity.MeunBean;

import java.util.List;

public interface MeunService {

    List<MeunBean> seleMeunByPid(Integer pid);
    int addMeun(MeunBean meunBean);

    String delMeunByid(Integer id);
}
