package com.starter.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.starter.app.domain.dto.AricleDTO;
import com.starter.app.domain.entity.Aricle;
import com.starter.app.domain.entity.Launage;
import com.starter.app.domain.entity.Topic;
import com.starter.app.domain.entity.User;
import com.starter.app.domain.ext.MyPage;
import com.starter.app.mapper.AricleMapper;
import com.starter.app.service.AricleService;
import com.starter.app.utils.OrikaUtils;
import com.starter.app.domain.vo.AricleVO;
import com.starter.app.domain.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;

/**
* author HJW
* description 
* date 2022-08-22
*/
@Service
public class AricleServiceImpl extends MPJBaseServiceImpl<AricleMapper, Aricle>  implements AricleService {

    final AricleMapper aricleMapper;
    public AricleServiceImpl(AricleMapper aricleMapper) {
        this.aricleMapper = aricleMapper;
    }

    @Override
    public Boolean addAricle(AricleDTO dto) {
        Assert.notNull(dto, "文章不能为空");
        Aricle aricle = OrikaUtils.convert(dto, Aricle.class);
        return save(aricle);
    }

    @Override
    public Boolean publishAricle(AricleDTO dto) {
        Assert.notNull(dto, "文章不能为空");
        Aricle aricle = OrikaUtils.convert(dto, Aricle.class);
        aricle.setStatus(1);
        return updateById(aricle);
    }

    @Override
    public AricleVO findById(Long id) {
        Assert.notNull(id, "id不能为空");
        Aricle aricle = aricleMapper.selectById(id);
        Assert.notNull(aricle, "文章不存在");
        Assert.isTrue(aricle.getStatus()==1, "文章不存在或未发布");
        return OrikaUtils.convert(aricle, AricleVO.class);
    }

    @Override
    public PageVO<AricleVO> queryPage(AricleDTO dto) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("read_count","desc");
        orderMap.put("id","desc");

        MPJLambdaWrapper<AricleDTO> queryWrapper = new MPJLambdaWrapper<>();
        queryWrapper
                .selectAll(Aricle.class)
                .select(User::getUsername)
                .selectAs(Launage::getName,"launage")
                .selectAs(Topic::getTitle,"topic")
                .leftJoin(User.class,User::getId,Aricle::getUId)
                .leftJoin(Launage.class,Launage::getId,Aricle::getLId)
                .leftJoin(Topic.class,Topic::getId,Aricle::getTId)
                .like(ObjectUtil.isNotEmpty(dto.getTitle()),Aricle::getTitle,dto.getTitle())
                .like(ObjectUtil.isNotEmpty(dto.getKeyword()),Aricle::getKeyword,dto.getKeyword());

        Page<AricleVO> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        aricleMapper.selectJoinPage(page, AricleVO.class,queryWrapper);
        return OrikaUtils.convertPageVo(page, AricleVO.class);
    }
}

