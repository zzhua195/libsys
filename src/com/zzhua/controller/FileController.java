package com.zzhua.controller;


import com.zzhua.domain.Attachment;
import com.zzhua.domain.User;
import com.zzhua.service.AttachmentService;
import com.zzhua.utils.DateUtil;
import com.zzhua.utils.RandomUtil;
import com.zzhua.utils.ResponseEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/toFile")
    public String toFile(Attachment attachment,Model model){
        List<Attachment> attachmentList = attachmentService.selectAll(attachment);
        model.addAttribute("attachmentList",attachmentList);
        return "file";
    }

    @GetMapping("toUpload")
    public String toUpload(Model model,Attachment attachment){
        List<Attachment> attachmentList = attachmentService.selectAll(attachment);
        model.addAttribute("attachment",attachmentList);
        return "upload";
    }

    @PostMapping("upload")
    @ResponseBody
    public ResponseEntity upload(MultipartFile mf){
        if (mf != null) {

            //获取文件保存的真实路径
            String realPath = servletContext.getRealPath("/file");
            //判断该文件夹是否存在
            File file = new File(realPath);
            if(!file.isDirectory()){
                file.mkdir();
            }

            //获取当前日期作为文件夹
            String currDateAsDir = RandomUtil.getCurrDateAsDir();
            File timeDir = new File(file, currDateAsDir);
            if (!timeDir.isDirectory()) {
                timeDir.mkdir();
            }

            //获取随机文件名
            String realname = mf.getOriginalFilename();
            if (!realname.contains(".")) {
                return new ResponseEntity(false,"不支持该文件上传");
            }
            String suffix = realname.substring(realname.lastIndexOf("."),realname.length());
            String filename = RandomUtil.getRandomNameByTime()+suffix;

            try {
                mf.transferTo(new File(timeDir,filename));
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity(false,"文件写入失败");
            }

            String filetype = mf.getContentType();
            double filesize = mf.getSize();

            User user = (User)(SecurityUtils.getSubject().getPrincipal());
            String username = user.getUsername();

            Date date = new Date();
            Attachment attachment = new Attachment(realname,filename,filetype,filesize,username,date);
            attachmentService.addAttachment(attachment);
        }else{
            return ResponseEntity.FAIL;
        }

        return ResponseEntity.SUCCESS;
    }

    @RequestMapping("download")
    @ResponseBody
    public ResponseEntity download(Integer id, HttpServletResponse response){
        if(id==null){
            return new ResponseEntity(false,"请选择你要下载的文件");
        }
        // 根据id获取文件的真实路径
        Attachment attachment = attachmentService.selectById(id);
        String virtualpath  = servletContext.getContextPath()+"/file/"+ DateUtil.getDateString(new Date())+"/"+attachment.getFilename();
        String realpath = servletContext.getRealPath(virtualpath);
        File file = new File(realpath);
        if(!file.isFile()){
            return new ResponseEntity(false,"找不到该文件了,请联系管理员");
        }
        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(attachment.getRealname(),"UTF-8"));
            ServletOutputStream os = response.getOutputStream();

            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[1024 * 1];
            int len;
            while((len=fis.read(bytes))!=-1){
                os.write(bytes,0,len);
            }
            os.close();
            fis.close();
            return new ResponseEntity(true,"下载成功");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.FAIL;

    }

}
