package com.udms.udmsystem.controller;

import com.udms.udmsystem.pojo.Task;
import com.udms.udmsystem.service.TaskService;
import com.udms.udmsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public Result addTask(
            @RequestBody Task task
    ){
        taskService.addTask(task);
        return Result.ok();
    }

    @GetMapping("/getAllTasks/{gid}")
    public Result getAllTasks(
            @PathVariable("gid") Integer gid
    ){
        List<Task> tasks = taskService.getAllTasksByGid(gid);
        return Result.ok(tasks);
    }

}
