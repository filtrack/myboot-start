package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.dto.LaunageDTO;
import com.starter.app.entity.Launage;
import com.starter.app.vo.LaunageVO;
import com.starter.app.vo.PageVO;

public interface LaunageService extends IService<Launage> {

    PageVO<LaunageVO> queryPage(LaunageDTO dto);

    Boolean addLaunage(LaunageDTO dto);
}
