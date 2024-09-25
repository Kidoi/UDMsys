package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.Message;

public interface MessageService extends IService<Message> {
    Message getMessageDetailById(Integer id);
}
