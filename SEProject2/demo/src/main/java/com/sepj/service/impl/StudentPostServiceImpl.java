package com.sepj.service.impl;

import com.sepj.entity.StudentPost;
import com.sepj.mapper.StudentPostMapper;
import com.sepj.mapper.UserMapper;
import com.sepj.service.StudentPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sepj
 * @since 2024-06-07
 */
@Service
public class StudentPostServiceImpl extends ServiceImpl<StudentPostMapper, StudentPost> implements StudentPostService {
    @Resource
    private StudentPostMapper studentPostMapper;
}
