package com.qf.controller;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
/**
 * description:
 * 用户管理功能控制层
 * 创建者: zhangqi
 * 创建时间: 2019/12/26
 * */
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

     /**
      * 查询所有用户
      * */
    @ResponseBody
    @RequestMapping("/sys/user/list")
    public DataGridResult listAllUsers(QueryDTO queryDTO){
        return sysUserService.listAllUsers(queryDTO);
    }

    /**
     * apache poi 实现excel导出用户信息
     * */
    @RequestMapping("/sys/user/export")
    public void exportUserInfo(HttpServletResponse response){
        Workbook workbook = this.sysUserService.exportUserInfo();
        // 设置相应内容为二进制流文件
        response.setContentType("application/octet-stream");
        String fileName = "员工信息表.xls";
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
            response.setHeader("content-disposition", "attachment;filename="+fileName);
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
