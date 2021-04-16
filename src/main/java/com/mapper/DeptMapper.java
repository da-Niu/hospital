package com.mapper;

import com.entity.DeptBean;
import com.entity.DeptBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    long countByExample(DeptBeanExample example);

    int deleteByExample(DeptBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeptBean record);

    int insertSelective(DeptBean record);

    List<DeptBean> selectByExample(DeptBeanExample example);

    DeptBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeptBean record, @Param("example") DeptBeanExample example);

    int updateByExample(@Param("record") DeptBean record, @Param("example") DeptBeanExample example);

    int updateByPrimaryKeySelective(DeptBean record);

    int updateByPrimaryKey(DeptBean record);

    List<Integer> initDeptPost(Integer deptId);

    int delPostByDeptId(Integer deptId);

    int saveDeptPost(@Param("deptId") Integer deptId,@Param("postIds")List<Integer> PostIds);

    List<DeptBean> seleDeptByUid(Integer uid);
}