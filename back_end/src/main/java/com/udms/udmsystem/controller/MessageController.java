package com.udms.udmsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.udms.udmsystem.pojo.Message;
import com.udms.udmsystem.pojo.MessageVO;
import com.udms.udmsystem.pojo.UserMessage;
import com.udms.udmsystem.service.MessageService;
import com.udms.udmsystem.service.UserMessageService;
import com.udms.udmsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserMessageService userMessageService;

    @GetMapping("/getMessageList/{pageNo}/{pageSize}")
    public Result getMessageList(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            Integer uid
    ){
        Page<MessageVO> page = new Page<>(pageNo, pageSize);
        IPage<MessageVO> pageRs = userMessageService.getMessageList(page, uid);
        return Result.ok(pageRs);
    }

    @GetMapping("/getMessageDetail/{mid}/{uid}")
    public Result getMessageDetail(
            @PathVariable("mid") Integer mid,
            @PathVariable("uid") Integer uid
    ){
        Message message = messageService.getMessageDetailById(mid);
        UserMessage userMessage = userMessageService.getUserMessageById(mid, uid);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", message.getId());
        map.put("title", message.getTitle());
        map.put("details", message.getDetails());
        map.put("createTime", message.getCreateTime());
        map.put("status", userMessage.getStatus());
        map.put("umid", userMessage.getId());
        map.put("did", message.getDid());

        return Result.ok(map);
    }

    @PostMapping("/updateUserMessage")
    public Result updateMessage(
            @RequestBody UserMessage message
    ){
        userMessageService.saveOrUpdate(message);
        return Result.ok();
    }

    @GetMapping("/getUnreadMessageCount/{uid}")
    public Result getUnreadCount(
            @PathVariable("uid") Integer uid
    ){
        Integer count = userMessageService.getUnreadCount(uid);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("count", count);
        return Result.ok(map);
    }

}
