package com.service;

import com.entity.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.DeptMapper;
import com.mapper.MeunMapper;
import com.mapper.PostMapper;
import com.mapper.UserMapper;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:谁
 * @Date:2021/4/7 19:27
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private MeunMapper meunMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<UserBean> Login(UserBean userBean) {
        UserBeanExample userBeanExample = new UserBeanExample();
        UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andUnameEqualTo(userBean.getUname());
        criteria.andUpwdEqualTo(userBean.getUpwd());
        List<UserBean> userBeans = userMapper.selectByExample(userBeanExample);
        return userBeans;
    }

    @Override
    public List<MeunBean> initTree(Long userId) {
        List<MeunBean> meunBeans = meunMapper.initTree(userId);
        return meunBeans;
    }

    @Override
    public Page getUserList(UserBean userBean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        UserBeanExample userBeanExample = null;
        if(userBean.getUname()!=null&&userBean.getUname().length()>=1){
            userBeanExample=new UserBeanExample();
            UserBeanExample.Criteria criteria = userBeanExample.createCriteria();
            criteria.andUnameLike("%"+userBean.getUname()+"%");
        }
        List<UserBean> userBeans = userMapper.selectByExample(userBeanExample);
        PageInfo pageInfo = new PageInfo(userBeans);
        long total = pageInfo.getTotal();

        Page page = new Page(pageNum+"",Integer.parseInt(String.valueOf(total)),pageSize+"");
        page.setList(userBeans);
        System.out.println(userBeans);
        return page;
    }

    @Override
    public List<Integer> seleUserDept(Integer id) {
        List<Integer> integers = userMapper.seleUserDept(id);
        System.out.println(integers);
        return integers;
    }

    @Override
    public List<DeptBean> seleAllDept() {
        List<DeptBean> deptBeans = deptMapper.selectByExample(null);
        return deptBeans;
    }

    @Override
    public int delDeptByUserId(Integer userId) {
        return userMapper.delDeptByUserId(userId);
    }

    @Override
    public int addUserDept(Integer userId, List<Integer> deptIds) {
        return userMapper.addUserDept(userId,deptIds);
    }

    @Override
    public List<DeptBean> seleDeptByUid(Integer uid) {
        return deptMapper.seleDeptByUid(uid);
    }

    @Override
    public List<DeptBean> initUserPost(Integer uid) {
        //根据用户id获取选中的部门
        List<DeptBean> deptBeans = deptMapper.seleDeptByUid(uid);
        System.out.println("deptBeans:"+deptBeans);

        for (int i=0;i<=deptBeans.size()-1;i++){
            //查询选中部门下的所有职业
            if(deptBeans.get(i).getId()!=null){
                List<PostBean> postBeans = postMapper.seleDeptPost(deptBeans.get(i).getId());
                deptBeans.get(i).setPostBeans(postBeans);
            }

            //查询选中部门下用户选中的职业
        }
        return deptBeans;
    }

    @Override
    public List<Integer> seleUserPost(Integer uid) {
        return postMapper.seleUserPost(uid);
    }

    @Override
    public int delUserPost(Integer userId) {
        return userMapper.delUserPost(userId);
    }

    @Override
    public int saveUserPost(Integer userId, List<Integer> postIds) {
        return userMapper.saveUserPost(userId,postIds);
    }

    @Override
    public List<String> seleUserUrl(Long userId) {
        return userMapper.seleUserUrl(userId);
    }
}
