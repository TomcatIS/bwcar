package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.SysUserMapper;
import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import com.qf.util.R;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * description:
 * 用户管理功能服务层
 * 创建时间：2019/12/26
 * 创建者：zhangqi
 * */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * “用户管理”：显示所有用户信息
     * */
    @Override
    public DataGridResult listAllUsers(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        if (queryDTO.getSort() != null && !queryDTO.getSort().equals("")) {
            queryDTO.setSort("user_id");
        }
        List<SysUser> allUsers = this.sysUserMapper.listAllUsers(queryDTO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(allUsers);
        DataGridResult dataGridResult = new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
        return dataGridResult;
    }

    /**
     * “用户管理”：删除用户
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R deleteUsers(List<Long> ids) {
        for (Long id : ids) {
            if (id < 4) {
                R.error("不能删除系统用户");
            }
        }
        int i = this.sysUserMapper.deleteUsers(ids);
        if (i > 0) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * “用户管理”：添加用户
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R addUser(SysUser sysUser) {
        String password = sysUser.getPassword();
        String username = sysUser.getUsername();
        Md5Hash newPass = new Md5Hash(password, username, 1024);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        Byte status = 1;
        SysUser currentUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
        Long creatUserId = currentUser.getUserId();
        sysUser.setPassword(newPass.toString());
        sysUser.setCreateTime(createTime);
        sysUser.setStatus(status);
        sysUser.setCreateUserId(creatUserId);
        int i = this.sysUserMapper.addUser(sysUser);
        if (i > 0) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * “用户管理”：修改用户信息
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R updateUserInfo(SysUser sysUser) {
        int i = this.sysUserMapper.updateUserInfo(sysUser);
        if (i > 0) {
            return R.ok("修改成功");
        } else {
            return R.error("修改失败");
        }
    }


    /**
     * “用户管理”：excel导出用户信息
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
}
