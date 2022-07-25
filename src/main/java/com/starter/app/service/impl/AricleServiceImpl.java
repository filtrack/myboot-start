package com.starter.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.dto.AricleDto;
import com.starter.app.entity.Aricle;
import com.starter.app.ext.MyPage;
import com.starter.app.mapper.AricleMapper;
import com.starter.app.service.AricleService;
import com.starter.app.utils.OrikaUtils;
import com.starter.app.vo.AricleVo;
import com.starter.app.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;

@Service
public class AricleServiceImpl extends ServiceImpl<AricleMapper, Aricle> implements AricleService {

    final AricleMapper aricleMapper;

    public AricleServiceImpl(AricleMapper aricleMapper) {
        this.aricleMapper = aricleMapper;
    }

    @Override
    public Boolean addAricle(AricleDto dto) {
        Assert.notNull(dto, "文章不能为空");
        Aricle aricle = OrikaUtils.convert(dto, Aricle.class);
        return save(aricle);
    }

    @Override
    public Boolean publishAricle(AricleDto dto) {
        Assert.notNull(dto, "文章不能为空");
        Aricle aricle = OrikaUtils.convert(dto, Aricle.class);
        aricle.setStatus(1);
        return updateById(aricle);
    }

    @Override
    public AricleVo findById(Long id) {
        Assert.notNull(id, "id不能为空");
        Aricle aricle = aricleMapper.selectById(id);
        Assert.notNull(aricle, "文章不存在");
        Assert.isTrue(aricle.getStatus()==1, "文章不存在或未发布");
        return OrikaUtils.convert(aricle, AricleVo.class);
    }

    @Override
    public PageVo<AricleVo> queryPage(AricleDto dto) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("read_count","desc");
        QueryWrapper<Aricle> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(ObjectUtil.isNotEmpty(dto.getTitle()),"title",dto.getTitle());
        queryWrapper.like(ObjectUtil.isNotEmpty(dto.getKeyword()),"keyword",dto.getKeyword());
        queryWrapper.eq("status",1);
        Page<Aricle> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        aricleMapper.selectPage(page,queryWrapper);

        return OrikaUtils.convertPageVo(page, AricleVo.class);
    }
}




