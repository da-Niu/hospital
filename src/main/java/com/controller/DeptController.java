package com.controller;

import com.entity.PostBean;
import com.service.DeptService;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:谁
 * @Date:2021/4/8 18:29
 */
@Controller
@RequestMapping("/DeptController")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/getDeptList")
    @ResponseBody
    public Page getDeptList(Integer pageNum,Integer pageSize){
        return deptService.getDeptList(pageNum,pageSize);
    }
    @RequestMapping("/initDeptPost")
    @ResponseBody
    public Map<String,Object> initDeptPost(Integer deptId){
        Map<String,Object> map = new HashMap<>();
        List<Integer> deptPostId = deptService.initDeptPost(deptId);
        List<PostBean> postAll = deptService.getPostAll();
        map.put("deptPostId",deptPostId);
        map.put("postAll",postAll);
        return map;
    }
    @RequestMapping("/saveDeptPost")
    @ResponseBody
    public String saveDeptPost(@RequestBody List<Integer> deptPostId,Integer deptId){
        try {
            deptService.delPostByDeptId(deptId);
            deptService.saveDeptPost(deptId,deptPostId);
            return "职业分配成功";
        }catch (Exception e){
            e.printStackTrace();
            return "职业分配失败";
        }
    }
}
