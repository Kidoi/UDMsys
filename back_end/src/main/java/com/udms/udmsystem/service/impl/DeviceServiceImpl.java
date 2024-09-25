package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.udms.udmsystem.mapper.DeviceMapper;
import com.udms.udmsystem.pojo.*;
import com.udms.udmsystem.server.WebSocketServer;
import com.udms.udmsystem.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service("devService")
@Transactional
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {


    @Override
    public IPage<Device> getDeviceByOpt(Page<Device> pageParam, Device device){
        QueryWrapper<Device> queryWrapper = new QueryWrapper();

        queryWrapper.eq("gid", device.getGid());
        if(!StringUtils.isEmpty(device.getName())){
            queryWrapper.like("name", device.getName());
        }
        if(null != device.getType()){
            queryWrapper.like("type", device.getType());
        }
        queryWrapper.orderByAsc("id");
        Page<Device> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }


    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public List<DeviceVO> getDeviceVO(Integer gid){
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("gid", gid);
        List<Device> dl = deviceMapper.selectList(queryWrapper);
        List<DeviceVO> dVOl = new ArrayList();
        for(Device d : dl){
            Double[] position = new Double[2];
            position[0] = new BigDecimal(d.getLongitude() + (new Random().nextBoolean() ? -1 : 1 ) * Math.random() * 0.01).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
            position[1] = new BigDecimal(d.getLatitude() + (new Random().nextBoolean() ? -1 : 1 ) * Math.random() * 0.01).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
            DeviceVO dvo = DeviceVO.builder()
                    .id(d.getId())
                    .type(d.getType())
                    .position(position)
                    .build();
            dVOl.add(dvo);
        }
        return dVOl;
    }

    @Override
    public DeviceInfoVO getDeviceById(Integer did) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", did);
        Device device = baseMapper.selectOne(queryWrapper);
        DeviceInfoVO res = DeviceInfoVO.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .longitude(new BigDecimal(device.getLongitude() + (new Random().nextBoolean() ? -1 : 1 ) * Math.random() * 0.01).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue())
                .latitude(new BigDecimal(device.getLatitude() + (new Random().nextBoolean() ? -1 : 1 ) * Math.random() * 0.01).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue())
                .electricity(device.getElectricity())
                .humidity(new BigDecimal(51.00 + (new Random().nextBoolean() ? -1 : 1 ) * Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                .wind(new BigDecimal(1.43 + (new Random().nextBoolean() ? -1 : 1 ) * Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                .temperature(new BigDecimal(23.41 + (new Random().nextBoolean() ? -1 : 1 ) * Math.random()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                .build();
        return res;
    }

    @Override
    public DeviceCountVO getRealtimeDeviceCount(Integer gid) {
        QueryWrapper<Device> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("gid", gid);
        Integer allCount = baseMapper.selectCount(queryWrapper1);
        QueryWrapper<Device> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("gid",gid);
        queryWrapper2.eq("status",1);
        Integer onlineAllCount = baseMapper.selectCount(queryWrapper2);

        QueryWrapper<Device> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("gid", gid);
        queryWrapper3.eq("type",1);
        Integer uvaCount = baseMapper.selectCount(queryWrapper3);
        QueryWrapper<Device> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("gid",gid);
        queryWrapper4.eq("type",1);
        queryWrapper4.eq("status",1);
        Integer onlineUvaCount = baseMapper.selectCount(queryWrapper4);

        QueryWrapper<Device> queryWrapper5 = new QueryWrapper<>();
        queryWrapper5.eq("gid", gid);
        queryWrapper5.eq("type",2);
        Integer ugvCount = baseMapper.selectCount(queryWrapper5);
        QueryWrapper<Device> queryWrapper6 = new QueryWrapper<>();
        queryWrapper6.eq("gid",gid);
        queryWrapper6.eq("type",2);
        queryWrapper6.eq("status",1);
        Integer onlineUgvCount = baseMapper.selectCount(queryWrapper6);

        DeviceCountVO res = DeviceCountVO.builder()
                .allCount(allCount)
                .onlineAllCount(onlineAllCount)
                .uvaCount(uvaCount)
                .onlineUvaCount(onlineUvaCount)
                .ugvCount(ugvCount)
                .onlineUgvCount(onlineUgvCount)
                .build();
        return res;
    }

    @Override
    public DeviceDataVO getRealtimeDeviceData(Integer gid) {
        QueryWrapper<DeviceDataVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gid", gid);
        DeviceDataVO ddvo = deviceMapper.getRealtimeDeviceData(queryWrapper);
        DeviceDataVO res = DeviceDataVO.builder()
                .gid(ddvo.getGid())
                .humidity(new BigDecimal(ddvo.getHumidity()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                .wind(new BigDecimal(ddvo.getWind()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                .temperature(new BigDecimal(ddvo.getTemperature()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
                .build();
        return res;
    }

    @Override
    public Collection<Integer> getDevicesByOpt(Device device) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper();

        queryWrapper.eq("gid", device.getGid());
        if(!StringUtils.isEmpty(device.getName())){
            queryWrapper.like("name", device.getName());
        }
        if(null != device.getType()){
            queryWrapper.eq("type", device.getType());
        }
        queryWrapper.orderByAsc("id");
        List<Device> ds = baseMapper.selectList(queryWrapper);
        Collection<Integer> res = new ArrayList<>();
        for(Device d : ds){
            res.add(d.getId());
        }
        return res;
    }
}
