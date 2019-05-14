package com.itheima.domain;

import java.util.List;

/**
 * 分页POJO对象
 *
 * 当前页
 * 每页条数
 * 总条数
 * 总页数
 * 当前页数据
 *
 */
public class PageBean<T> {
    //当前页  --页面传参
    private Integer currPage;

    //每页条数  --页面传参
    private Integer pageSize;

    //总条数   --数据库查询
    private Long totalCount;
    //总页数   --计算  总条数/每页的条数    math.ceil(totalCount*1.0/pageSize)
    private Integer totalPage;
    //当前页数据  --数据库查询
    private List<T> list;

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

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
