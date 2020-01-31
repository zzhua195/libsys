package com.zzhua.utils;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/30
 * <p>
 * Description:
 */


public class PageBean {
    private Integer currPage = 1;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPage;

    public PageBean(Integer currPage, Integer pageSize) {
        this.currPage = currPage;
        this.pageSize = pageSize;
    }

    public PageBean() {
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {

        this.totalCount = totalCount;
        this.totalPage = (int)Math.ceil( 1.0*totalCount / pageSize);
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
