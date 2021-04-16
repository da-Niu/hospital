package com.service;

import com.entity.DeptBean;
import com.entity.MeunBean;
import com.entity.UserBean;
import com.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public List<UserBean> Login(UserBean userBean);

    List<MeunBean> initTree(Long userId);
    Page getUserList(UserBean userBean, Integer pageNum, Integer pageSize);

    List<Integer> seleUserDept(Integer id);

    List<DeptBean> seleAllDept();

    int delDeptByUserId(Integer userId);

    int addUserDept(Integer userId,List<Integer> deptIds);

    List<DeptBean> seleDeptByUid(Integer uid);

    List<DeptBean> initUserPost(Integer uid);

    List<Integer> seleUserPost(Integer uid);

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
    int saveUserPost(@Param("userId") Integer userId, @Param("postIds") List<Integer> postIds);

    /**
     * 根据用户id查询用的url权限
     * @param userId
     * @return
     */
    List<String> seleUserUrl(Long userId);

}
