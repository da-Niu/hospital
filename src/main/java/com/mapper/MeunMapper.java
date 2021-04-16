package com.mapper;

import com.entity.MeunBean;
import com.entity.MeunBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeunMapper {
    long countByExample(MeunBeanExample example);

    int deleteByExample(MeunBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MeunBean record);

    int insertSelective(MeunBean record);

    List<MeunBean> selectByExample(MeunBeanExample example);

    MeunBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MeunBean record, @Param("example") MeunBeanExample example);

    int updateByExample(@Param("record") MeunBean record, @Param("example") MeunBeanExample example);

    int updateByPrimaryKeySelective(MeunBean record);

    int updateByPrimaryKey(MeunBean record);

    List<MeunBean> initTree(Long userId);
}