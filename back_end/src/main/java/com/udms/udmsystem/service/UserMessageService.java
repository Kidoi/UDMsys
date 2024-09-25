package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.MessageVO;
import com.udms.udmsystem.pojo.UserMessage;

public interface UserMessageService extends IService<UserMessage> {
    IPage<MessageVO> getMessageList(Page<MessageVO> page, Integer uid);

    UserMessage getUserMessageById(Integer mid,Integer uid);

    Integer getUnreadCount(Integer uid);

    Boolean hasMessage(Integer sid);
}
