package com.zzhua.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/2/2
 * <p>
 * Description:
 */


public class DateUtil {
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    public static String getDateString(Date date){
        if (date==null){
            return "";
        }
        return sdfDate.format(date);
    }
}
