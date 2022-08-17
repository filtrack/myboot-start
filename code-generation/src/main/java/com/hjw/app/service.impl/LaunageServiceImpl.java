package com.hjw.app.service.impl;

import com.hjw.app.service.LaunageService;
import com.hjw.app.mapper.LaunageMapper;
import org.springframework.stereotype.Service;


/**
* author HJW
* description 
* date 2022-08-16
*/
@Service
public class LaunageServiceImpl implements LaunageService {

    final LaunageMapper launageMapper;
    public LaunageServiceImpl(LaunageMapper launageMapper) {
        this.launageMapper = launageMapper;
    }
}

