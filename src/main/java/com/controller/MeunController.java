package com.controller;

import com.entity.MeunBean;
import com.service.MeunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:谁
 * @Date:2021/4/8 11:27
 */
@Controller
@RequestMapping("/MeunConreoller")
public class MeunController {
    @Autowired
    private MeunService meunService;
    @RequestMapping("/seleMeunByPid")
    @ResponseBody
    public List<MeunBean> seleMeunByPid(Integer pid){
       return meunService.seleMeunByPid(pid);
    }

    @RequestMapping("/addMeun")
    @ResponseBody
    public String addMeun(@RequestBody MeunBean meunBean){
        System.out.println("meunBean："+meunBean);
        try {
            meunService.addMeun(meunBean);
            return "添加成功";
        }catch (Exception e){
            e.printStackTrace();
            return "添加失败";
        }
    }

    @RequestMapping("/delMeunByid")
    @ResponseBody
    public String delMeunByid(Integer id){
       try {
           String s = meunService.delMeunByid(id);
           return s;
       }catch (Exception e){
           e.printStackTrace();
           return "删除失败";
       }
    }
}
