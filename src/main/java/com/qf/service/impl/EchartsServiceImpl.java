package com.qf.service.impl;

import com.qf.dao.EchartsMapper;
import com.qf.pojo.Tag;
import com.qf.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * “echarts图标显示”服务层接口实现
 *  @author zhangqi
 *  创建时间：2020/2/24
 * */
@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    private EchartsMapper echartsMapper;

    /**
     * 折线图和柱状图
     * */
    @Override
    public Map generateLineAndBarChart() {
        List<Tag> tags = this.echartsMapper.listTags();
        List<String> xAxisData = new LinkedList<>();
        List<Long> seriesData = new LinkedList<>();
        for (Tag tag : tags) {
            xAxisData.add(tag.getName());
            seriesData.add(tag.getClickCount());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("xAxis", xAxisData);
        map.put("seriesData", seriesData);
        return map;
    }

    @Override
    public Map generatePieChart() {
        List<Tag> tags = this.echartsMapper.listTags();
        List<String> legendData = new LinkedList<>();
        List<Map<String, Object>> seriesData = new LinkedList<>();
        String name;
        Long value;
        for (Tag tag : tags) {
            name = tag.getName();
            value = tag.getClickCount();
            legendData.add(name);
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", name);
            hashMap.put("value", value);
            seriesData.add(hashMap);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("legendData", legendData);
        map.put("seriesData", seriesData);
        return map;
    }
}
