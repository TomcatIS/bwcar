package com.qf.controller;

import com.qf.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 咨询管理控制层
 * 创建时间：2020/2/13
 * */
@Controller
public class ArticalController {
    @RequestMapping("/ytupload")
    public R upload(@RequestParam("mypic") MultipartFile multipartFile) {

    }
}
