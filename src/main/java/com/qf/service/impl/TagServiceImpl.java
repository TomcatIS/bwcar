package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.TagMapper;
import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.Tag;
import com.qf.pojo.TagExample;
import com.qf.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签服务层实现
 * 创建时间：2020/2/13
 * */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public int addTag(Tag tag) {
        int i = this.tagMapper.insertSelective(tag);
        return i;
    }

    @Override
    public int delTag(Integer id) {
        int i = this.tagMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int updateTag(Tag tag) {
        int i = this.tagMapper.updateByPrimaryKeySelective(tag);
        return i;
    }

    @Override
    public Tag getTagById(Integer id) {
        Tag tag = this.tagMapper.selectByPrimaryKey(id);
        return tag;
    }

    @Override
    public DataGridResult getByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        TagExample example = new TagExample();
        if(queryDTO.getSort()!=null&&!queryDTO.getSort().equals("")){
            example.setOrderByClause("id "+queryDTO.getOrder());
        }
        List<Tag> tags = tagMapper.selectByExample(example);
        PageInfo<Tag> info = new PageInfo<>(tags);
        long total = info.getTotal();
        DataGridResult dataGridResult = new DataGridResult(total,tags);
        return dataGridResult;
    }
}
