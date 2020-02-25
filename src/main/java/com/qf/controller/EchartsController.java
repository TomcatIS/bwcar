package com.qf.controller;

import com.qf.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * “echarts图表显示”控制器
 * @author zhangqi
 * 创建时间：2020/2/24
 * */
@Controller
public class EchartsController {
    @Autowired
    private EchartsService echartsService;

    /**
     * 折线图
     * */
    @ResponseBody
    @RequestMapping("/sys/echarts/line")
    public Map generateLineChart() {
        return this.echartsService.generateLineAndBarChart();
    }

    /**
     * 柱状图
     * */
    @ResponseBody
    @RequestMapping("/sys/echarts/bar")
    public Map generateBarChart() {
        return this.echartsService.generateLineAndBarChart();
    }

    /**
     * 饼状图
     * */
    @ResponseBody
    @RequestMapping("/sys/echarts/pie")
    public Map generatePieChart() {
        return this.echartsService.generatePieChart();
    }
}
