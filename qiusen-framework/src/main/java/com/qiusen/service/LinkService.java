package com.qiusen.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiusen.domain.dto.AdminLinkListDto;
import com.qiusen.domain.entity.Link;
import com.qiusen.domain.vo.AdminLinkDetailVo;
import com.qiusen.domain.vo.LinkVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;

/**
 * 友链(Link)表服务接口
 *
 * @author qiusen
 * @since 2024-03-19 20:42:16
 */
public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();

    PageVo queryList(Integer pageNum, Integer pageSize, AdminLinkListDto adminLinkListDto);

    void add(LinkVo linkVo);

    AdminLinkDetailVo getLinkById(Integer id);

    void updateLink(AdminLinkDetailVo adminLinkDetailVo);
}
