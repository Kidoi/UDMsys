package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.Task;

import java.util.List;

public interface TaskService extends IService<Task> {
    void addTask(Task task);

    List<Task> getAllTasksByGid(Integer gid);
}
