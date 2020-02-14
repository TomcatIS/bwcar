package com.qf.service;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.Tag;
/**
 * 标签服务层接口
 * 创建时间：2020/2/13
 * */
public interface TagService {

    int addTag(Tag tag);

    int delTag(Integer id);

    int updateTag(Tag tag);

    Tag getTagById(Integer id);

    DataGridResult getByPage(QueryDTO queryDTO);
}
