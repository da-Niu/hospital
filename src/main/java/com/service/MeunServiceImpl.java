package com.service;

import com.entity.MeunBean;
import com.entity.MeunBeanExample;
import com.mapper.DeptMapper;
import com.mapper.MeunMapper;
import com.mapper.PostMapper;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:谁
 * @Date:2021/4/8 11:32
 */
@Service
public class MeunServiceImpl implements MeunService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private MeunMapper meunMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<MeunBean> seleMeunByPid(Integer pid) {
        MeunBeanExample meunBeanExample = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = meunBeanExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<MeunBean> meunBeans = meunMapper.selectByExample(meunBeanExample);
        return meunBeans;
    }

    @Override
    public int addMeun(MeunBean meunBean) {
        System.out.println(meunBean);
        return meunMapper.insertSelective(meunBean);
    }

    @Override
    public String delMeunByid(Integer id) {
        List<MeunBean> meunBeans = seleMeunByPidToDel(id);
        //删除它的所有下级
        delMeun(meunBeans);
        //删除它本身
        meunMapper.deleteByPrimaryKey(id);
        return "删除成功";
    }

    private void delMeun(List<MeunBean> list){
        //判断集合中是否有值
        if(list!=null&&list.size()>=1){
            //循环集合
            for (int i=0;i<=list.size()-1;i++){
                //根据id删除
                meunMapper.deleteByPrimaryKey(list.get(i).getId());
                System.out.println("删除了："+list.get(i).getId());
                //根据id查询 看下级是否还有数据
                List<MeunBean> meunBeans = seleMeunByPidToDel(list.get(i).getId());
                //调用自己去删除
                delMeun(meunBeans);
            }
        }
    }

    /**
     * 根据id去查询下级
     * @param pid
     * @return
     */
    private List<MeunBean> seleMeunByPidToDel(Integer pid) {
        MeunBeanExample meunBeanExample = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = meunBeanExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<MeunBean> meunBeans = meunMapper.selectByExample(meunBeanExample);
        return meunBeans;
    }
}
