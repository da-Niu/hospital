package com.service;

import com.entity.PostBean;
import com.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptService {
    Page getDeptList(Integer pageNum,Integer pageSize);

    List<Integer> initDeptPost(Integer deptId);

    List<PostBean> getPostAll();

    int delPostByDeptId(Integer deptId);

    int saveDeptPost(@Param("deptId") Integer deptId, @Param("postIds")List<Integer> PostIds);
}
