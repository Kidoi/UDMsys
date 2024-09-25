package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.PathMapper;
import com.udms.udmsystem.pojo.Path;
import com.udms.udmsystem.service.PathService;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Service("patSerive")
@Transactional
public class PathServiceImpl extends ServiceImpl<PathMapper, Path> implements PathService {
    @Override
    public List<Path> getPathByOpts(Integer did, Timestamp startTime, Timestamp endTime) {
        QueryWrapper<Path> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("did", did);
        queryWrapper.allEq(map);
        if(null != startTime){
            queryWrapper.gt("time", startTime);
        }
        if(null != endTime){
            queryWrapper.lt("time", endTime);
        }
        queryWrapper.orderByAsc("time");
        return baseMapper.selectList(queryWrapper);
    }
}
