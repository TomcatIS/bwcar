package com.qf.dao;

import com.qf.pojo.Tag;

import java.util.List;

/**
 * “echarts图标显示”持久层接口
 *  * @author zhangqi
 *  * 创建时间：2020/2/24
 * */
public interface EchartsMapper {

    /** 查询所有标签信息 */
    List<Tag> listTags();
}
