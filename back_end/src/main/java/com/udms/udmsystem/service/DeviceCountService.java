package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.DeviceCount;
import com.udms.udmsystem.pojo.DeviceCountVO;

import java.util.List;

public interface DeviceCountService extends IService<DeviceCount> {
    List<DeviceCount> getDeviceCountRecords(Integer gid);
}
