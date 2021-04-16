package com.entity;

import java.util.List;

public class DeptBean {
    private Long id;

    private String deptname;

    private List<PostBean> postBeans;

    public List<PostBean> getPostBeans() {
        return postBeans;
    }

    public void setPostBeans(List<PostBean> postBeans) {
        this.postBeans = postBeans;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

}