package com.starter.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.dto.LaunageDto;
import com.starter.app.entity.Launage;
import com.starter.app.ext.MyPage;
import com.starter.app.mapper.LaunageMapper;
import com.starter.app.service.LaunageService;
import com.starter.app.utils.OrikaUtils;
import com.starter.app.vo.LaunageVo;
import com.starter.app.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;


@Service
public class LaunageServiceImpl extends ServiceImpl<LaunageMapper, Launage> implements LaunageService {

    final LaunageMapper launageMapper;

    public LaunageServiceImpl(LaunageMapper launageMapper) {
        this.launageMapper = launageMapper;
    }

    @Override
    public PageVo<LaunageVo> queryPage(LaunageDto dto) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("id","asc");

        QueryWrapper<Launage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(dto.getName()),"name",dto.getName());
        Page<Launage> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        launageMapper.selectPage(page,queryWrapper);

        return OrikaUtils.convertPageVo(page, LaunageVo.class);
    }

    @Override
    public Boolean addLaunage(LaunageDto dto) {
        Assert.notNull(dto, "语言信息不能为空");
        Launage launage = OrikaUtils.convert(dto, Launage.class);
        return save(launage);
    }
}




