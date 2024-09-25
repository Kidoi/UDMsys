package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.*;

import java.util.Collection;
import java.util.List;

public interface DeviceService extends IService<Device> {

    IPage<Device> getDeviceByOpt(Page<Device> pageParam, Device device);

    List<DeviceVO> getDeviceVO(Integer gid);

    DeviceInfoVO getDeviceById(Integer did);

    DeviceCountVO getRealtimeDeviceCount(Integer gid);

    DeviceDataVO getRealtimeDeviceData(Integer gid);

    Collection<Integer> getDevicesByOpt(Device device);
}
