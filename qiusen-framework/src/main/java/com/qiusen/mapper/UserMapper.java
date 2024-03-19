package com.qiusen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiusen.domain.entity.Link;
import com.qiusen.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper  extends BaseMapper<User> {
}
