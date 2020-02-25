package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.TagMapper;
import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.Tag;
import com.qf.service.TagService;
import com.qf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签服务层实现
 * 创建时间：2020/2/13
 * 创建者：zhangqi
 * */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    /**
     * 标签管理”：显示所有标签信息
     * */
    @Override
    public DataGridResult listTags(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        List<Tag> tags = this.tagMapper.listTags(queryDTO);
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        DataGridResult dataGridResult = new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
        return dataGridResult;
    }

    /**
     * “标签管理”：删除标签信息
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R deleteTags(List<Long> ids) {
        for (Long id : ids) {
            if (id < 4) {
                return R.error("不能删除系统标签");
            }
        }
        int i = this.tagMapper.deleteTags(ids);
        if (i > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * “标签管理”：新增标签
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R addTag(Tag tag) {
        int i = this.tagMapper.addTag(tag);
        if (i > 0) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * “标签管理”：修改标签
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R updateTag(Tag tag) {
        int i = this.tagMapper.updateTag(tag);
        if (i > 0) {
            return R.ok("修改成功");
        } else {
            return R.error("修改失败");
        }
    }
}
