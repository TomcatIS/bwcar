package com.qf.service;

import java.util.Map;

/**
 * “echarts图标显示”服务层接口
 * @author zhangqi
 * 创建时间：2020/2/24
 * */
public interface EchartsService {

    /** 折线图和柱状图 */
    Map generateLineAndBarChart();

    /** 饼状图 */
    Map generatePieChart();
}
