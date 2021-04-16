package com.controller;

import com.entity.MeunBean;
import com.service.PostService;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:谁
 * @Date:2021/4/8 16:25
 */
@Controller
@RequestMapping("/PostController")
public class PostController {
    @Autowired
    private PostService postService;


    @RequestMapping("/getPostList")
    @ResponseBody
    public Page getPostList(Integer pageNum, Integer pageSize) {
        Page postList = postService.getPostList(pageNum, pageSize);
        return postList;
    }

    @RequestMapping("/initMeun")
    @ResponseBody
    public List<MeunBean> initMeun(Integer id) {
        List<MeunBean> meunBeans = postService.initMeun(id);
        return meunBeans;
    }
    @RequestMapping("/savePostMeun")
    @ResponseBody
    public String savePostMeun(@RequestBody Integer[] meunIds, Integer postId){
        try {
            postService.delMeunByPid(postId);
            postService.savePostMeun(meunIds,postId);
            return "权限修改成功";
        }catch (Exception e){
            e.printStackTrace();
            return "权限修改失败";
        }
    }
}
