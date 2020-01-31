package com.zzhua.controller;

import com.zzhua.domain.Book;
import com.zzhua.domain.User;
import com.zzhua.service.BookService;
import com.zzhua.utils.PageBean;
import com.zzhua.vo.BookVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/30
 * <p>
 * Description:
 */

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("list")
    public String list(BookVo bookVo, Model model, PageBean pageBean){
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user",principal);
        pageBean.setPageSize(3);
        List<Book> bookList = bookService.SelectAll(bookVo,pageBean);
        model.addAttribute("bookList",bookList);
        model.addAttribute("pageBean",pageBean);
        return "bookList";
    }

    @RequestMapping("borrowBook")
    public String borrowBook(BookVo bookVo){
        bookService.updateById(bookVo);
        return "redirect:list";
    }

}
