package com.qf.service;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.Tag;
import com.qf.util.R;

import java.util.List;

/**
 * 标签服务层接口
 * 创建时间：2020/2/13
 * */
public interface TagService {

    /**“标签管理”：显示所有标签信息 */
    DataGridResult listTags(QueryDTO queryDTO);

    /**“标签管理”：删除标签信息 */
    R deleteTags(List<Long> ids);

    /**“标签管理”：新增标签 */
    R addTag(Tag tag);

    /**“标签管理”：修改标签 */
    R updateTag(Tag tag);
}
