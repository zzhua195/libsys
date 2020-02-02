package com.zzhua.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/2/2
 * <p>
 * Description:
 */


public class RandomUtil {

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    private static Random random = new Random();

    /**
     * 获取4位随机数字
     * @return
     */
    private static String getFourNum(){
        return String.valueOf(random.nextInt(9000)+1000);
    }

    public static String getRandomNameByTime(){
        return sdf1.format(new Date())+getFourNum();
    }

    public static String getCurrDateAsDir(){
        return sdf2.format(new Date());
    }

}
