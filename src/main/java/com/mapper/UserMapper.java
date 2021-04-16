package com.mapper;

import com.entity.UserBean;
import com.entity.UserBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserBeanExample example);

    int deleteByExample(UserBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    List<UserBean> selectByExample(UserBeanExample example);

    UserBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByExample(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    List<Integer> seleUserDept(Integer id);

    int delDeptByUserId(Integer userId);

    int addUserDept(@Param("userId") Integer userId,@Param("deptIds")List<Integer> deptIds);

    /**
     * 删除用户下的职业
     * @param userId 用户id
     * @return
     */
    int delUserPost(Integer userId);

    /**
     * 保存用户的职业
     * @param userId    用户id
     * @param postIds   职业id
     * @return
     */
    int saveUserPost(@Param("userId") Integer userId,@Param("postIds") List<Integer> postIds);

    /**
     * 根据用户id查询用的url权限
     * @param userId
     * @return
     */
    List<String> seleUserUrl(Long userId);
}