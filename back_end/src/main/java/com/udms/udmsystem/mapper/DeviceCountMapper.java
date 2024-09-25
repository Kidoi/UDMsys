package com.udms.udmsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.udms.udmsystem.pojo.DeviceCount;
import com.udms.udmsystem.pojo.DeviceCountVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceCountMapper extends BaseMapper<DeviceCount> {
}
