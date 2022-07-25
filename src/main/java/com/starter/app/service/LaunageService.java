package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.dto.LaunageDto;
import com.starter.app.entity.Launage;
import com.starter.app.vo.LaunageVo;
import com.starter.app.vo.PageVo;

public interface LaunageService extends IService<Launage> {

    PageVo<LaunageVo> queryPage(LaunageDto dto);

    Boolean addLaunage(LaunageDto dto);
}
