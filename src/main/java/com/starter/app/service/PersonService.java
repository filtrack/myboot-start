package com.starter.app.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.app.dto.PageVo;
import com.starter.app.entity.Person;

import java.util.Map;

/**
 * 服务接口
 */
public interface PersonService {
    boolean add(Person person);

    PageVo queryPage(Map map);
}
