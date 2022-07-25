package com.starter.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.dto.TopicDto;
import com.starter.app.entity.Topic;
import com.starter.app.ext.MyPage;
import com.starter.app.mapper.TopicMapper;
import com.starter.app.service.TopicService;
import com.starter.app.utils.OrikaUtils;
import com.starter.app.vo.PageVo;
import com.starter.app.vo.TopicVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;


@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    final TopicMapper topicMapper;

    public TopicServiceImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Override
    public PageVo<TopicVo> queryPage(TopicDto dto) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("id","asc");

        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(dto.getTitle()),"title",dto.getTitle());
        Page<Topic> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        topicMapper.selectPage(page,queryWrapper);

        return OrikaUtils.convertPageVo(page, TopicVo.class);
    }

    @Override
    public Boolean addTopic(TopicDto dto) {
        Assert.notNull(dto, "专题信息不能为空");
        Topic topic = OrikaUtils.convert(dto, Topic.class);
        return save(topic);
    }
}




