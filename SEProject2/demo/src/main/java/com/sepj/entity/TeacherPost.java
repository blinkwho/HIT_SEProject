package com.sepj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author sepj
 * @since 2024-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TeacherPost对象", description="")
public class TeacherPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String subject;

    private String telephoneNumber;

    private Integer teacherId;

    private Integer price;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
