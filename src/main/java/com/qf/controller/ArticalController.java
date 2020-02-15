package com.qf.controller;

import com.qf.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 咨询管理控制层
 * 创建时间：2020/2/13
 * */
@Controller
public class ArticalController {
    @RequestMapping("/ytupload")
    public R upload(@RequestParam("mypic") MultipartFile multipartFile, HttpServletRequest request) {
        String fileName = multipartFile.getOriginalFilename();
        File destination = new File("D:\\workspace\\IdeaProjects\\bwcar\\file" + fileName);
        try {
            multipartFile.transferTo(destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("file", fileName);
    }
}
