package com.udms.udmsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.udms.udmsystem.pojo.*;
import com.udms.udmsystem.service.DeviceCountService;
import com.udms.udmsystem.service.DeviceService;
import com.udms.udmsystem.service.PathService;
import com.udms.udmsystem.service.PicService;
import com.udms.udmsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/v1/api/device")
public class DeviceController {
    @Autowired
    private  DeviceService deviceService;
    @Autowired
    private DeviceCountService deviceCountService;
    @Autowired
    private PicService picService;
    @Autowired
    private PathService pathService;

    @GetMapping("/getDevices/{pageNo}/{pageSize}")
    public Result getDevices(
        @PathVariable("pageNo") Integer pageNo,
        @PathVariable("pageSize") Integer pageSize,
        Device device
    ){
        Page<Device> page = new Page<>(pageNo, pageSize);
        IPage<Device> pageRs = deviceService.getDeviceByOpt(page, device);
        return Result.ok(pageRs);
    }

    @PostMapping("/updateDevice")
    public Result updateDevice(@RequestBody Device device){
        deviceService.saveOrUpdate(device);
        return Result.ok();
    }

    @DeleteMapping("/deleteDevice")
    public Result deleteDevice(@RequestBody List<Integer> ids){
        deviceService.removeByIds(ids);
        return Result.ok();
    }

    @GetMapping("/getDeviceInfo/{did}")
    public Result getDeviceInfo(
            @PathVariable("did") Integer did
    ){
        DeviceInfoVO deviceInfo = deviceService.getDeviceById(did);
        return Result.ok(deviceInfo);
    }

    @GetMapping("/getRealtimeDeviceCount/{gid}")
    public Result getDeviceCount(
            @PathVariable("gid") Integer gid
    ){
        DeviceCountVO deviceCountVO = deviceService.getRealtimeDeviceCount(gid);
        return Result.ok(deviceCountVO);
    }

    @GetMapping("/getRealtimeDeviceData/{gid}")
    public Result getDeviceData(
            @PathVariable("gid") Integer gid
    ){
        DeviceDataVO deviceDataVO = deviceService.getRealtimeDeviceData(gid);
        return Result.ok(deviceDataVO);
    }

    @GetMapping("/getDeviceCountRecords/{gid}")
    public Result getDeviceCountRecords(
            @PathVariable("gid") Integer gid
    ){
        List<DeviceCount> res = deviceCountService.getDeviceCountRecords(gid);
        return Result.ok(res);
    }



    @GetMapping("/getPic/{pageNo}/{pageSize}")
    public Result getPic(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            Device device,
            Long startTime,
            Long endTime
    ){
        Collection<Integer> ids = deviceService.getDevicesByOpt(device);
        if(ids.isEmpty()){
            return Result.ok();
        }
        Page<Pic> page = new Page<>(pageNo, pageSize);
        Timestamp sTime = null;
        Timestamp eTime = null;
        if(startTime!=null){
            sTime = new Timestamp(startTime);
        }
        if(endTime!=null) {
            eTime = new Timestamp(endTime);
        }
        IPage<Pic> pageRs = picService.getPicByOpt(page, ids, sTime, eTime);
        return Result.ok(pageRs);
    }

    @GetMapping("getPath/{did}")
    public Result getPath(
            @PathVariable("did") Integer did,
            Long startTime,
            Long endTime
    ){
        Map<String, Object> map = new HashMap<>();
        DeviceInfoVO deviceInfo = deviceService.getDeviceById(did);
        if(deviceInfo==null){
            return Result.fail().message("查询失败！");
        }
        map.put("name", deviceInfo.getName());

        Timestamp sTime = null;
        Timestamp eTime = null;
        if(startTime!=null){
            sTime = new Timestamp(startTime);
        }
        if(endTime!=null) {
            eTime = new Timestamp(endTime);
        }

        List<Path> paths = pathService.getPathByOpts(did, sTime, eTime);
        map.put("points",paths);
        return Result.ok(map);
    }

}
