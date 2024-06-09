package com.sepj.service.impl;

import com.sepj.entity.TeacherPost;
import com.sepj.mapper.TeacherPostMapper;
import com.sepj.service.TeacherPostService;
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
public class TeacherPostServiceImpl extends ServiceImpl<TeacherPostMapper, TeacherPost> implements TeacherPostService {

    @Resource
    private TeacherPostMapper teacherPostMapper;
}
