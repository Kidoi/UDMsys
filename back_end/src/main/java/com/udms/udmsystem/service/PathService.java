package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.Path;

import java.sql.Timestamp;
import java.util.List;

public interface PathService extends IService<Path> {

    List<Path> getPathByOpts(Integer did, Timestamp startTime, Timestamp endTime);
}
