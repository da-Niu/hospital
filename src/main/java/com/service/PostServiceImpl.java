package com.service;

import com.entity.MeunBean;
import com.entity.PostBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.MeunMapper;
import com.mapper.PostMapper;
import com.mapper.UserMapper;
import com.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:谁
 * @Date:2021/4/8 16:26
 */
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private MeunMapper meunMapper;


    @Override
    public Page getPostList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PostBean> postBeans = postMapper.selectByExample(null);
        PageInfo pageInfo = new PageInfo(postBeans);
        long total = pageInfo.getTotal();
        Page page = new Page(pageNum+"",Integer.parseInt(String.valueOf(total)),pageSize+"");
        page.setList(postBeans);
        return page;
    }

    @Override
    public List<MeunBean> initMeun(Integer postId) {
        List<MeunBean> meunBeans = meunMapper.selectByExample(null);
        List<Integer> meunIds = postMapper.selePostMeun(postId); //选中的id
        System.out.println(meunIds);
        for (MeunBean meunBean: meunBeans) {
            for (int i=0;i<= meunIds.size()-1;i++){
                if(meunBean.getId()==meunIds.get(i)){
                    meunBean.setChecked(true);
                    break;
                }
            }

        }
        return meunBeans;
    }

    @Override
    public int savePostMeun(Integer[] meunIds, Integer postIds) {
        return postMapper.savePostMeun(meunIds,postIds);
    }

    @Override
    public int delMeunByPid(Integer postId) {
        return postMapper.delMeunByPid(postId);
    }


}
