package com.zzhua.utils;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/2/1
 * <p>
 * Description:
 */


public class ResponseEntity<T> {

    private final static ResponseEntity SUCCESS = new ResponseEntity(true,"操作成功");
    private final static ResponseEntity FAIL = new ResponseEntity(true,"操作失败");

    private boolean flag;//操作结果 true成功 false失败
    private String msg; //操作返回消息
    private List<T> dataList; //返回的数据

    public ResponseEntity(boolean flag,String msg){
        this.flag = flag;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
