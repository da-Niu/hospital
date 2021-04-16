package com.service;

import com.entity.MeunBean;
import com.utils.Page;

import java.util.List;

public interface PostService {
    Page getPostList(Integer pageNum,Integer pageSize);

    List<MeunBean> initMeun(Integer postId);

    int savePostMeun(Integer[] meunIds,Integer postIds);

    int delMeunByPid(Integer postId);
}
