package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.domain.dto.TopicDTO;
import com.starter.app.domain.entity.Topic;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.domain.vo.TopicVO;

public interface TopicService extends IService<Topic> {
    PageVO<TopicVO> queryPage(TopicDTO topicDto);

    Boolean addTopic(TopicDTO dto);
}
