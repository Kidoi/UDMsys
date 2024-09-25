package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.DeviceCountMapper;
import com.udms.udmsystem.pojo.DeviceCount;
import com.udms.udmsystem.pojo.DeviceCountVO;
import com.udms.udmsystem.service.DeviceCountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("decService")
@Transactional
public class DeviceCountServiceImpl extends ServiceImpl<DeviceCountMapper, DeviceCount> implements DeviceCountService {
    @Override
    public List<DeviceCount> getDeviceCountRecords(Integer gid) {
        QueryWrapper<DeviceCount> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("gid", gid);
        queryWrapper.allEq(map);
        return baseMapper.selectList(queryWrapper);
    }
}