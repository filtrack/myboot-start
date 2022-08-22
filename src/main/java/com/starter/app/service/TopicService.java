package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.dto.TopicDTO;
import com.starter.app.entity.Topic;
import com.starter.app.vo.PageVO;
import com.starter.app.vo.TopicVO;

public interface TopicService extends IService<Topic> {
    PageVO<TopicVO> queryPage(TopicDTO topicDto);

    Boolean addTopic(TopicDTO dto);
}
