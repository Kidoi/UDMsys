package com.udms.udmsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.udms.udmsystem.pojo.CommentVO;
import com.udms.udmsystem.pojo.Device;
import com.udms.udmsystem.pojo.DeviceDataVO;
import com.udms.udmsystem.pojo.DeviceVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMapper extends BaseMapper<Device> {

    DeviceDataVO getRealtimeDeviceData(@Param(Constants.WRAPPER) Wrapper<DeviceDataVO> wrapper);
}
