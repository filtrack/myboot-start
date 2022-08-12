package com.cool.mapper;

import com.cool.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjw
 * @since 2022-08-11 15:29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
