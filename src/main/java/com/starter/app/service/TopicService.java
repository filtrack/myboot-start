package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.dto.TopicDto;
import com.starter.app.entity.Topic;
import com.starter.app.vo.PageVo;
import com.starter.app.vo.TopicVo;

public interface TopicService extends IService<Topic> {
    PageVo<TopicVo> queryPage(TopicDto topicDto);

    Boolean addTopic(TopicDto dto);
}
