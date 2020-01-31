package com.zzhua.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzhua.domain.Book;
import com.zzhua.mapper.BookMapper;
import com.zzhua.service.BookService;
import com.zzhua.utils.PageBean;
import com.zzhua.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/30
 * <p>
 * Description:
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;


    @Override
    public List<Book> SelectAll(BookVo bookVo, PageBean pageBean) {
        Page<Book> bookPage = PageHelper.startPage(pageBean.getCurrPage(), pageBean.getPageSize());
        List<Book> bookList = bookMapper.selectAll(bookVo);

        pageBean.setTotalCount((int)bookPage.getTotal());
        return bookList;
    }

    @Override
    public void updateById(BookVo bookVo) {
        bookMapper.updateByPrimaryKeySelective(bookVo);
    }
}
