package com.sepj.common;

import lombok.Data;
//返回的数据
@Data
public class Result {

    private int code;//200 成功 400 失败
    private String msg;//成功 失败
    private Long total;//数据条数
    private Object data;//数据，一般是entity里面定义的类构成，说白了就是数据库的条目组成

    public static Result fail(){
        return result(400, "失败", 0L, null);
    }

    public static Result succeed(){
        return result(200, "成功",0L, null);
    }

    public static Result succeed(Object data){
        return result(200, "成功", 0L, data);
    }

    public static Result succeed(Object data, Long total){
        return result(200, "成功", total, data);
    }

    private static Result result(int code, String msg, Long total, Object data){
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }
}