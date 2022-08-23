package com.starter.app.service;

import com.github.yulichang.base.MPJBaseService;
import com.starter.app.domain.dto.AricleDTO;
import com.starter.app.domain.entity.Aricle;
import com.starter.app.domain.vo.AricleVO;
import com.starter.app.domain.vo.PageVO;

public interface AricleService extends MPJBaseService<Aricle> {

    Boolean addAricle(AricleDTO dto);

    Boolean publishAricle(AricleDTO dto);

    AricleVO findById(Long id);

    PageVO<AricleVO> queryPage(AricleDTO dto);
}
