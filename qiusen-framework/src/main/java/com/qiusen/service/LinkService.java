package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.entity.Link;
import com.qiusen.enums.ResponseResult;

/**
 * 友链(Link)表服务接口
 *
 * @author qiusen
 * @since 2024-03-19 20:42:16
 */
public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
