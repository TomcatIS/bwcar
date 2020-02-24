package com.qf.controller;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.Tag;
import com.qf.service.TagService;
import com.qf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * “标签管理”控制层
 * 创建时间：2020/2/13
 * 创建者：zhangqi
 * */

@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * “标签管理”：显示所有标签信息
     * */
    @ResponseBody
    @RequestMapping("/sys/tag/list")
    public DataGridResult listTags(QueryDTO queryDTO) {
        return this.tagService.listTags(queryDTO);
    }

    /**
     * “标签管理”：删除标签信息
     * */
    @ResponseBody
    @RequestMapping("/sys/tag/del")
    public R deleteTags(@RequestBody List<Long> ids) {
        return this.tagService.deleteTags(ids);
    }

    /**
     * “标签管理”：新增标签
     * */
    @ResponseBody
    @RequestMapping("/sys/tag/add")
    public R addTag(@RequestBody Tag tag) {
        return this.tagService.addTag(tag);
    }

    /**
     * “标签管理”：修改标签
     * */
    @ResponseBody
    @RequestMapping("/sys/tag/update")
    public R updateTag(@RequestBody Tag tag) {
        return this.tagService.updateTag(tag);
    }
}
