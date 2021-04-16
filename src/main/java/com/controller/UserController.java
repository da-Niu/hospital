package com.controller;

import com.entity.DeptBean;
import com.entity.MeunBean;
import com.entity.UserBean;
import com.service.UserService;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:谁
 * @Date:2021/4/7 19:26
 */
@Controller
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/GoLogin")
    @ResponseBody
    public String goLogin(@RequestBody UserBean userBean, HttpSession session){
        System.out.println(userBean);
        List<UserBean> login = userService.Login(userBean);
        System.out.println(login);
        if(login!=null&&login.size()>=1){
            List<String> userUrls = userService.seleUserUrl(login.get(0).getId());
            session.setAttribute("user",login);
            session.setAttribute("userUrls",userUrls);
            return "登录成功";
        }else{
            return "登录失败";
        }
    }

    @RequestMapping("/initTree")
    @ResponseBody
    public List<MeunBean> initTree(HttpSession session){
        List<UserBean> user = (List<UserBean>)session.getAttribute("user");
        Long userId = user.get(0).getId();
        List<MeunBean> meunBeans = userService.initTree(userId);
        return meunBeans;
    }
    @RequestMapping("/getUserList")
    @ResponseBody
    public Page getUserList(@RequestBody UserBean userBean, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        Page page = userService.getUserList(userBean, pageNum, pageSize);
        return page;
    }
    @RequestMapping("/initUserDept")
    @ResponseBody
    public Map<String,Object> initUserDept(Integer id){
        List<Integer> integers = userService.seleUserDept(id);
        List<DeptBean> deptBeans = userService.seleAllDept();
        Map<String,Object> map = new HashMap<>();
        map.put("userDeptId",integers);
        map.put("deptBeans",deptBeans);
        return map;
    }
    @RequestMapping("/saveUserDept")
    @ResponseBody
    public String saveUserDept(@RequestBody List<Integer> userDeptId,Integer userId){
        System.out.println(userDeptId);
        System.out.println(userId);
        try {
            userService.delDeptByUserId(userId);
            userService.addUserDept(userId,userDeptId);
            return "分配部门成功";
        }catch (Exception e){
            e.printStackTrace();
            return "分配部门成功";
        }
    }

    @RequestMapping("/initUserPost")
    @ResponseBody
    public Map<String,Object> initUserPost(Integer uid){
        List<DeptBean> deptBeans = userService.initUserPost(uid);

        List<Integer> uidList = userService.seleUserPost(uid);
        Map<String,Object> map = new HashMap<>();
        map.put("deptBeans",deptBeans);
        map.put("uidList",uidList);
        return  map;
    }
    @RequestMapping("/saveUserPost")
    @ResponseBody
    public String saveUserPost(@RequestBody List<Integer> postIds,Integer userId){
        System.out.println("postIds："+postIds);
        System.out.println("userId："+userId);
        try {
            userService.delUserPost(userId);
            if(postIds.size()>0){
                userService.saveUserPost(userId,postIds);
            }
            return "职业分配成功";
        }catch (Exception e){
            e.printStackTrace();
            return "职业分配失败";
        }
    }
}
