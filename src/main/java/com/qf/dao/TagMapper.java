package com.qf.dao;

import com.qf.dto.QueryDTO;
import com.qf.pojo.Tag;
import com.qf.pojo.TagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * “标签管理”持久层接口
 * */
public interface TagMapper {

    /**“标签管理”：显示所有标签信息 */
    List<Tag> listTags(QueryDTO queryDTO);

    /**“标签管理”：删除标签信息 */
    int deleteTags(List<Long> ids);

    /**“标签管理”：新增标签 */
    int addTag(Tag tag);

    /**“标签管理”：修改标签 */
    int updateTag(Tag tag);
}