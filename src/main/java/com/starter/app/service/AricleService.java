package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.dto.AricleDTO;
import com.starter.app.entity.Aricle;
import com.starter.app.vo.AricleVO;
import com.starter.app.vo.PageVO;

public interface AricleService extends IService<Aricle> {

    Boolean addAricle(AricleDTO dto);

    Boolean publishAricle(AricleDTO dto);

    AricleVO findById(Long id);

    PageVO<AricleVO> queryPage(AricleDTO dto);
}
