package com.hjw.app.service.impl;

import com.hjw.app.service.TopicService;
import com.hjw.app.mapper.TopicMapper;
import org.springframework.stereotype.Service;


/**
* author HJW
* description 专题
* date 2022-08-16
*/
@Service
public class TopicServiceImpl implements TopicService {

    final TopicMapper topicMapper;
    public TopicServiceImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }
}

