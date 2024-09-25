package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.Device;
import com.udms.udmsystem.pojo.Pic;
import com.udms.udmsystem.pojo.PicVO;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface PicService extends IService<Pic> {

    IPage<Pic> getPicByOpt(Page<Pic> pageParam, Collection<Integer> ids, Timestamp startTime, Timestamp endTime);
}
