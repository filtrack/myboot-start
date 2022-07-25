package com.starter.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.entity.Topic;
import com.starter.app.mapper.TopicMapper;
import com.starter.app.service.TopicService;
import org.springframework.stereotype.Service;


@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

}




