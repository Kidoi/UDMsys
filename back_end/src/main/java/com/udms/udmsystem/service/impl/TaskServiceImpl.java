package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.TaskMapper;
import com.udms.udmsystem.pojo.Task;
import com.udms.udmsystem.service.TaskService;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Service("tasService")
@Transactional
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
    @Override
    public void addTask(Task task) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Task addTask = Task.builder()
                .gid(task.getGid())
                .status(task.getStatus())
                .mlist(task.getMlist())
                .startTime(task.getStartTime())
                .endTime(task.getEndTime())
                .createTime(timestamp)
                .details(task.getDetails())
                .title(task.getTitle())
                .build();
        baseMapper.insert(addTask);
    }

    @Override
    public List<Task> getAllTasksByGid(Integer gid) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("gid", gid);
        queryWrapper.allEq(map);
        queryWrapper.orderByDesc("create_time");
        return baseMapper.selectList(queryWrapper);
    }
}
