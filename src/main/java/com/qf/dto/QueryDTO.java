package com.qf.dto;
/**
 * description:
 * 封装显示菜单管理页面时向后端传递的数据
 * @author zhangqi
 * @created 2019/1/3
 * */
public class QueryDTO {
    private Integer limit;
    // 排序方式：升序，降序
    private String order;
    private Integer offset;
    // 排序字段
    private String sort;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    // 搜索的内容
    private String search;
}
