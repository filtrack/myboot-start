package com.cool.service.impl;

import com.cool.entity.Topic;
import com.cool.mapper.TopicMapper;
import com.cool.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专题 服务实现类
 * </p>
 *
 * @author hjw
 * @since 2022-08-11 15:29
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

}
