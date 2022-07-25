package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.dto.AricleDto;
import com.starter.app.entity.Aricle;
import com.starter.app.vo.AricleVo;
import com.starter.app.vo.PageVo;

public interface AricleService extends IService<Aricle> {

    Boolean addAricle(AricleDto dto);

    Boolean publishAricle(AricleDto dto);

    AricleVo findById(Long id);

    PageVo<AricleVo> queryPage(AricleDto dto);
}
