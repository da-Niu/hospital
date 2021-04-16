package com.mapper;

import com.entity.PostBean;
import com.entity.PostBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    long countByExample(PostBeanExample example);

    int deleteByExample(PostBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PostBean record);

    int insertSelective(PostBean record);

    List<PostBean> selectByExample(PostBeanExample example);

    PostBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PostBean record, @Param("example") PostBeanExample example);

    int updateByExample(@Param("record") PostBean record, @Param("example") PostBeanExample example);

    int updateByPrimaryKeySelective(PostBean record);

    int updateByPrimaryKey(PostBean record);

    List<Integer> selePostMeun(Integer postId);

    int savePostMeun(@Param("meunIds") Integer[] meunIds,@Param("postId")Integer postId);

    List<PostBean> seleDeptPost(Long did);

    List<Integer> seleUserPost(Integer uid);

    int delMeunByPid(Integer postId);
}