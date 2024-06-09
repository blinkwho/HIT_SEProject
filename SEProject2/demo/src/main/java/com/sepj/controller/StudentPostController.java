package com.sepj.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sepj.common.QueryPageParam;
import com.sepj.common.Result;
import com.sepj.entity.StudentPost;
import com.sepj.service.StudentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
@RestController
@RequestMapping("/student-post")
public class StudentPostController {
    @Autowired
    private StudentPostService studentPostService;

//    列出所有的条目
    @GetMapping("/listAll")
    public Result listAll(){
        return Result.succeed(studentPostService.list());
    }
//  与后面区分，这里的date格式为"yyyy-MM-ddTHH:mm:ss"
    //    向表中插入表项
    @PostMapping("/insert")
    public Result insert(@RequestBody StudentPost studentPost){
        return studentPostService.save(studentPost)?Result.succeed():Result.fail();
    }

    //    根据id删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return studentPostService.removeById(Integer.parseInt(id))? Result.succeed() : Result.fail();
    }

    //    根据id查找
    @GetMapping("/findById")
    public Result findById(@RequestParam int id){
        List list = studentPostService.lambdaQuery().eq(StudentPost::getId,id).list();
        return list.size()>0?Result.succeed(list):Result.fail();
    }

    //    根据提交需求的人的id查找
    @GetMapping("/findBySId")
    public Result findBySId(@RequestParam int studentId){
        List list = studentPostService.lambdaQuery().eq(StudentPost::getStudentId,studentId).list();
        return list.size()>0?Result.succeed(list):Result.fail();
    }

//    根据QueryPageParam来进行分页的返回以及查询
/*
*   支持查询的字符串如下：
*   subject：学科
*   lowPrice：价格下界
*   highPrice：价格上界（两个均需要给出）
*   studentId：学生的id，由于不是外键，需要前端双表一起查
*   teacherId：同前
*   startDate：起始日期
*   endDate：终止日期（同上）
*   其中，日期的字符串格式为"yyyy-MM-dd HH:mm:ss"
* */
    @PostMapping("/pageSearch")
    public Result pageSearch(@RequestBody QueryPageParam queryPageParam){
        HashMap map = queryPageParam.getParam();
//        设置分页的基本参数
        Page<StudentPost> page = new Page<>();
        page.setCurrent(queryPageParam.getPageNum());
        page.setSize(queryPageParam.getPageSize());
//        进行模糊查询
        LambdaQueryWrapper<StudentPost> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        subject筛选
        if((String)map.get("subject") != null){
            System.out.println((String)map.get("subject"));
            lambdaQueryWrapper.eq(StudentPost::getSubject,(String)map.get("subject"));
        }
//        price筛选
        if((String)map.get("lowPrice") != null && map.get("highPrice") != null){
            try{
                Integer low = Integer.parseInt((String)map.get("lowPrice"));
                Integer high = Integer.parseInt((String)map.get("highPrice"));
                lambdaQueryWrapper.between(StudentPost::getPrice,low,high);
            }catch (NumberFormatException e){
                return Result.fail();
            }
        }
//        studentId筛选
        if((String)map.get("studentId") != null){
            lambdaQueryWrapper.like(StudentPost::getStudentId,(String)map.get("studentId"));
        }


//        datetime筛选
        if((String)map.get("startDate") != null){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                // 将字符串解析为 LocalDateTime 对象
                LocalDateTime startDate = LocalDateTime.parse((String)map.get("startDate"),formatter);
                lambdaQueryWrapper.gt(StudentPost::getStartDate,startDate);
            }catch (DateTimeParseException e){
                return Result.fail();
            }catch (NullPointerException e){
                return Result.fail();
            }
        }

        if((String)map.get("endDate") != null){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                // 将字符串解析为 LocalDateTime 对象
                LocalDateTime endDate = LocalDateTime.parse((String)map.get("endDate"), formatter);
                lambdaQueryWrapper.lt(StudentPost::getEndDate,endDate);
            }catch (DateTimeParseException e){
                return Result.fail();
            }catch (NullPointerException e){
                return Result.fail();
            }
        }
//        放入分页的数据中
        IPage data= studentPostService.page(page,lambdaQueryWrapper);
        System.out.println("total=" + data.getTotal());

        return Result.succeed(data.getRecords(), data.getTotal());
    }
}
