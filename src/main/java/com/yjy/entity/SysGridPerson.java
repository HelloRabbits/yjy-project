package com.yjy.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 编制外员工， 网格员与用户之前的关联关系
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysGridPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_grid_person", type = IdType.ASSIGN_UUID)
    private String idGridPerson;

    /**
     * 网格员主键
     */
    private String idGmPerson;

    /**
     * 个人主键（不能重复）
     */
    private String idPerson;

    private String createUser;

    private LocalDateTime createTime;

    private String updateUser;

    private LocalDateTime updateTime;


}
