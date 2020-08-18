package com.gosang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.gosang.enums.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* <p>
*
* </p>
*
* @author gosang
* @since 2020-08-17
*/
@Data
@EqualsAndHashCode(callSuper = false)
  public class User implements Serializable {

  private static final long serialVersionUID=1L;

    /**
   * 主键
   */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
   * 登录名
   */
    private String loginName;

    /**
   * 用户名
   */
    private String userName;

    /**
   * 密码
   */
    private String password;

    /**
   * 性别(1:男 0：女)
   */
    private GenderEnum gender;

    /*这个注解是MyBatis Plus的，代表这个字段在表中不存在，inert的时候不会带上他就不会报错*/
    @TableField(exist = false)
    private Integer sex;

    /**
   * 身份证号
   */
    private String identityCode;

    /**
   * 邮箱
   */
    private String email;

    /**
   * 手机
   */
    private String mobile;

  private String fileName;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
