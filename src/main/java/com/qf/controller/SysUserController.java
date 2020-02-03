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
 * 测试数据库连接是否成功
 * @author zhangqi
 * 2019/12/26
 * */
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/sys/user/list")
    public DataGridResult selectAllUser(QueryDTO queryDTO){
        return sysUserService.selectAllUser(queryDTO);
    }

    @RequestMapping("/sys/user/export")
    public void exportUser(HttpServletResponse response){
        Workbook workbook = this.sysUserService.exportUser();
        // 支持任何类型的文件
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
