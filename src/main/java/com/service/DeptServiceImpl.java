package com.service;

import com.entity.DeptBean;
import com.entity.PostBean;
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
 * @Date:2021/4/8 18:31
 */
@Service
public class DeptServiceImpl implements DeptService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private MeunMapper meunMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public Page getDeptList(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<DeptBean> deptBeans = deptMapper.selectByExample(null);
        PageInfo pageInfo = new PageInfo(deptBeans);
        long total = pageInfo.getTotal();
        Page page = new Page(pageNum+"",Integer.parseInt(String.valueOf(total)),pageSize+"");
        page.setList(deptBeans);
        return page;
    }

    @Override
    public List<Integer> initDeptPost(Integer deptId) {
        return deptMapper.initDeptPost(deptId);
    }

    @Override
    public List<PostBean> getPostAll() {
        return postMapper.selectByExample(null);
    }

    @Override
    public int delPostByDeptId(Integer deptId) {
        return deptMapper.delPostByDeptId(deptId);
    }

    @Override
    public int saveDeptPost(Integer deptId, List<Integer> PostIds) {
        return deptMapper.saveDeptPost(deptId,PostIds);
    }
}
