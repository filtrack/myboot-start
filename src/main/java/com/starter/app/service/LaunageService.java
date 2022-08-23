package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.domain.dto.LaunageDTO;
import com.starter.app.domain.entity.Launage;
import com.starter.app.domain.vo.LaunageVO;
import com.starter.app.domain.vo.PageVO;

public interface LaunageService extends IService<Launage> {

    PageVO<LaunageVO> queryPage(LaunageDTO dto);

    Boolean addLaunage(LaunageDTO dto);
}
