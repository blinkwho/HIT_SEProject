package com.sepj.common;

import lombok.Data;

import java.util.HashMap;

//分页参数的设定封装，查询数据的时候传入
@Data
public class QueryPageParam {
    //default
    private static int PAGE_SIZE = 20;
    private static int PAGE_NUM = 1;
//页的大小
    private int pageSize = PAGE_SIZE;
//所在的页码的数据
    private int pageNum = PAGE_NUM;
//这里主要用于分页查询，里面写筛选的条件（条件见controller的几个文档）
    private HashMap param = new HashMap();
}
