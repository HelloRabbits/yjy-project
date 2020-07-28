package com.yjy.service.impl;

import com.yjy.entity.SysGridPerson;
import com.yjy.mapper.SysGridPersonMapper;
import com.yjy.service.ISysGridPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 编制外员工， 网格员与用户之前的关联关系 服务实现类
 * </p>
 *
 * @author zhangjl
 * @since 2020-07-28
 */
@Service
public class SysGridPersonServiceImpl extends ServiceImpl<SysGridPersonMapper, SysGridPerson> implements ISysGridPersonService {

}
