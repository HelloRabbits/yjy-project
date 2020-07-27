package com.yjy.bean.dto.dingding;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author zhangjl
 * @description 部门列表
 * @date 2020-07-27 10:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class DeptListInfo extends DingDingBaseDto {

    private List<DeptDetailInfo> department;
}
