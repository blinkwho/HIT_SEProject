package com.sepj.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sepj.common.QueryPageParam;
import com.sepj.common.Result;
import com.sepj.entity.User;
import com.sepj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sepj
 * @since 2024-06-07
 */
//由于我设定的端口是8090（在application.yml里面更改），所以调取数据的网址为http://localhost:8090
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println(user.getName() + " " + user.getPassword());
        List<User> list = userService.lambdaQuery().eq(User::getName,user.getName()).
                eq(User::getPassword,user.getPassword()).list();
        return list.size()>0?Result.succeed(list.get(0)):Result.fail();
    }
//    列出所有的user条目
//    http://localhost:8090/user/listAll
//    传回的Result的data当中就是数据集合
    @GetMapping("/listAll")
    public Result listAll(){
        return Result.succeed(userService.list());
    }

//    向表中插入表项
//    http://localhost:8090/user/insert
//    传入的数据应构造成User的类，如下
/*
{
    "name":"doctor",
    "password":"12345",
    "tel":"11112222333",
    "roleId":1
}
*/
    @PostMapping("/insert")
    public Result insert(@RequestBody User user){
        return userService.save(user)?Result.succeed():Result.fail();
    }

//    根据id删除
//    http://localhost:8090/user/del?id=1
//    只用传入上面的网址，id写在网址里面
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return userService.removeById(Integer.parseInt(id))? Result.succeed() : Result.fail();
    }

//    根据id查找
//    http://localhost:8090/user/findById?id=1
    @GetMapping("/findById")
    public Result findById(@RequestParam int id){
        List list = userService.lambdaQuery().eq(User::getId,id).list();
        return list.size()>0?Result.succeed(list):Result.fail();
    }

//    根据姓名的查找，用于返回查找的分页数据
//    http://localhost:8090/user/findByName?name=doc
    @GetMapping("/findByName")
    public Result searchByName(@RequestParam String name){
        List list = userService.lambdaQuery().like(User::getName,name).list();
        return list.size()>0?Result.succeed(list):Result.fail();
    }

//    根据QueryPageParam来进行分页的返回以及查询
//    http://localhost:8090/student-post/pageSearch
//    传入的是QueryPageParam类
//    想查什么直接添加进去param里面
//    支持name和roleId筛选
/*
{
    "pageSize":1,
    "pageNum":1,
    "data":{
        "name":"doc"
    }
}
*/
    @PostMapping("/pageSearch")
    public Result pageSearch(@RequestBody QueryPageParam queryPageParam){
        HashMap map = queryPageParam.getParam();
//        设置分页的基本参数
        Page<User> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());
//        进行模糊查询
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        name筛选
        if((String)map.get("name") != null){
            lambdaQueryWrapper.like(User::getName,(String)map.get("name"));
        }
//
        if((String)map.get("roleId") != null){
            try{
                Integer inputNum = Integer.parseInt((String)map.get("roleId"));
                lambdaQueryWrapper.eq(User::getRoleId,inputNum);
            }catch (NumberFormatException e){
                return  Result.fail();
            }
        }
//        放入分页的数据中
        IPage data= userService.page(page,lambdaQueryWrapper);
        System.out.println("total=" + data.getTotal());

        return Result.succeed(data.getRecords(), data.getTotal());
    }

}
