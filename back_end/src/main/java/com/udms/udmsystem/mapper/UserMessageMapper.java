package com.udms.udmsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.udms.udmsystem.pojo.MessageVO;
import com.udms.udmsystem.pojo.UserMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageMapper extends BaseMapper<UserMessage> {

    IPage<MessageVO> getMessageList(Page<MessageVO> page, @Param(Constants.WRAPPER) Wrapper<MessageVO> wrapper);
}
