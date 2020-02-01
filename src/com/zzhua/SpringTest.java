package com.zzhua;

import com.zzhua.domain.Book;
import com.zzhua.domain.User;
import com.zzhua.mapper.BookMapper;
import com.zzhua.mapper.UserMapper;
import com.zzhua.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/30
 * <p>
 * Description:
 */

@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testUserService(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.commit(false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insert(new User("member01","456",new Date()));
    }

    @Test
    public void testBookMapper(){
        List<Book> bookList = bookMapper.selectAll(null);
        System.out.println(bookList);
    }

    @Test
    public void testMd5Encrypto(){
        Md5Hash hashedPwd = new Md5Hash("admin", ByteSource.Util.bytes(("admin" + "abcd").getBytes()), 2);
        System.out.println(hashedPwd.toString().length());
    }


}
