package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.MessageMapper;
import com.udms.udmsystem.mapper.UserMessageMapper;
import com.udms.udmsystem.pojo.MessageVO;
import com.udms.udmsystem.pojo.UserMessage;
import com.udms.udmsystem.service.UserMessageService;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Builder
@Service("usmService")
@Transactional
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

    @Resource
    private UserMessageMapper userMessageMapper;

    @Override
    public IPage<MessageVO> getMessageList(Page<MessageVO> page, Integer uid) {
        QueryWrapper<MessageVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        queryWrapper.orderByAsc("status");
        queryWrapper.orderByDesc("create_time");
        return userMessageMapper.getMessageList(page, queryWrapper);
    }

    @Override
    public UserMessage getUserMessageById(Integer mid, Integer uid) {
        QueryWrapper<UserMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mid", mid);
        queryWrapper.eq("uid", uid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer getUnreadCount(Integer uid) {
        QueryWrapper<UserMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        queryWrapper.eq("status", 0);
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Boolean hasMessage(Integer uid) {
        QueryWrapper<UserMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        queryWrapper.eq("status", 0);
        return baseMapper.selectCount(queryWrapper)!=0;
    }
}
