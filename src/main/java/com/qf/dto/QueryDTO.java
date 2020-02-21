package com.qf.dto;
/**
 * description:
 * 封装分页查询，模糊查询字段
 * @author zhangqi
 * @created 2019/1/3
 * */
public class QueryDTO {
    /**每页条数*/
    private Integer limit;
    /**排序方式：升序，降序*/
    private String order;
    /**每页的起始位置*/
    private Integer offset;
    /**排序字段*/
    private String sort;
    /**搜索的内容*/
    private String search;

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

    @Override
    public String toString() {
        return "QueryDTO{" +
                "limit=" + limit +
                ", order='" + order + '\'' +
                ", offset=" + offset +
                ", sort='" + sort + '\'' +
                ", search='" + search + '\'' +
                '}';
    }
}
