package com.qiusen.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiusen.constants.SystemConstants;
import com.qiusen.domain.dto.AdminLinkListDto;
import com.qiusen.domain.entity.Link;
import com.qiusen.domain.vo.AdminLinkDetailVo;
import com.qiusen.domain.vo.LinkVo;
import com.qiusen.domain.vo.PageVo;
import com.qiusen.enums.ResponseResult;
import com.qiusen.mapper.LinkMapper;
import com.qiusen.service.LinkService;
import com.qiusen.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author qiusen
 * @since 2024-03-19 20:42:16
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public PageVo queryList(Integer pageNum, Integer pageSize, AdminLinkListDto adminLinkListDto) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(adminLinkListDto.getName()), Link::getName, adminLinkListDto.getName());
        queryWrapper.eq(StringUtils.hasText(adminLinkListDto.getStatus()), Link::getStatus, adminLinkListDto.getStatus());
        Page<Link> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        page(page, queryWrapper);
        return new PageVo(BeanCopyUtils.copyBeanList(page.getRecords(), LinkVo.class), page.getTotal());
    }

    @Override
    public void add(LinkVo linkVo) {
        save(BeanCopyUtils.copyBean(linkVo, Link.class));
    }

    @Override
    public AdminLinkDetailVo getLinkById(Integer id) {
        return BeanCopyUtils.copyBean(getById(id), AdminLinkDetailVo.class);
    }

    @Override
    public void updateLink(AdminLinkDetailVo adminLinkDetailVo) {
        updateById(BeanCopyUtils.copyBean(adminLinkDetailVo, Link.class));
    }
}
