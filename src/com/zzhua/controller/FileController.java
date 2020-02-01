package com.zzhua.controller;


import com.zzhua.domain.Attachment;
import com.zzhua.service.AttachmentService;
import com.zzhua.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/2/1
 * <p>
 * Description:
 */

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("/toFile")
    public String toFile(Attachment attachment){
        attachmentService.selectAll(attachment);
        return "file";
    }

    @RequestMapping("upload")
    @ResponseBody
    public ResponseEntity upload(){
        return
    }
}
