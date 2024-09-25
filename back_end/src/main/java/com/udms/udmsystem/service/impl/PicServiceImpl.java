package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.PicMapper;
import com.udms.udmsystem.pojo.Pic;
import com.udms.udmsystem.pojo.PicVO;
import com.udms.udmsystem.service.PicService;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Builder
@Service("picService")
@Transactional
public class PicServiceImpl extends ServiceImpl<PicMapper, Pic> implements PicService {

    @Override
    public IPage<Pic> getPicByOpt(Page<Pic> pageParam, Collection<Integer> ids, Timestamp startTime, Timestamp endTime) {
        QueryWrapper<Pic> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("did", ids);
        if(null != startTime){
            queryWrapper.gt("time", startTime);
        }
        if(null != endTime){
            queryWrapper.lt("time", endTime);
        }
        Page<Pic> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }
}
