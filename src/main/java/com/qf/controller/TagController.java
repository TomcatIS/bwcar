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
 * 标签控制层
 * 创建时间：2020/2/13
 * */

@Controller
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/sys/tag/list")
    @ResponseBody
    public DataGridResult getTag(QueryDTO queryDTO) {
        return this.tagService.getByPage(queryDTO);
    }

    @RequestMapping("/sys/tag/del")
    @ResponseBody
    public R delTagById(@RequestBody List<Integer> ids) {
        int delNum = 0;
        for (Integer x : ids) {
            delNum = this.tagService.delTag(x);
        }
        if (delNum != 0) {
            return R.ok("删除成功");
        }else {
            return R.error("删除失败");
        }
    }

    @RequestMapping("/sys/tag/save")
    @ResponseBody
    public R saveTag(@RequestBody Tag tag) {
        int i = this.tagService.addTag(tag);
        if (i != 0) {
            return R.ok("添加成功");
        }else {
            return R.error("添加失败");
        }
    }

    @RequestMapping("/sys/tag/info/{id}")
    @ResponseBody
    public R getTagById(@PathVariable("id") Integer id){
        Tag tag = this.tagService.getTagById(id);
        if (tag != null) {
            return R.ok().put("tag", tag);
        }else {
            return R.error();
        }
    }

    @RequestMapping("/sys/tag/update")
    @ResponseBody
    public R updateTag(@RequestBody Tag tag) {
        int i = this.tagService.updateTag(tag);
        if (i != 0) {
            return R.ok("更新成功");
        }else {
            return R.error("更新失败");
        }
    }
}
