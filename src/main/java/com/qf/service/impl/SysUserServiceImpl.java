package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.SysUserMapper;
import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * description:
 * 用户管理功能服务层
 * 创建时间：
 * 创建者：zhangqi
 * */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询所有用户
     * */
    @Override
    public DataGridResult listAllUsers(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        List<SysUser> allUsers = this.sysUserMapper.listAllUsers(queryDTO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(allUsers);
        DataGridResult dataGridResult = new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
        return dataGridResult;
    }

    /**
     * apache poi 实现excel导出用户信息
     * */
    @Override
    public Workbook exportUserInfo() {
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建一个sheet
        Sheet sheet = workbook.createSheet();
        String[] titles = {"用户id", "用户名", "邮箱", "手机", "创建时间"};
        String[] columns = {"userId", "username", "email", "mobile", "createTime"};
        // 创建标题行
        Row titleRow = sheet.createRow(0);
        // 设置标题行的值
        for (int i = 0; i < titles.length; i++){
            // 创建标题行的单元格
            Cell cell = titleRow.createCell(i);
            // 设置单元格的值
            cell.setCellValue(titles[i]);
        }
        // 设置每一行的值
        List<Map<String, Object>> mapList = this.sysUserMapper.exportUserInfo();
        for (int i = 0; i < mapList.size(); i++){
            Row row = sheet.createRow(i + 1);
            // 一个List元素代表一行的数据
            Map<String, Object> map = mapList.get(i);
            for (int j = 0; j < columns.length; j++){
                Cell cell = row.createCell(j);
                Object obj = map.get(columns[j]);
                cell.setCellValue(obj + "");
            }
        }
        return workbook;
    }

    @Override
    public SysUser getUserByName(String username) {
        return this.sysUserMapper.getUserByName(username);
    }
}
