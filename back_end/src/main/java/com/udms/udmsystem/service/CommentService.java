package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.Comment;
import com.udms.udmsystem.pojo.CommentVO;
import com.udms.udmsystem.pojo.Group;

public interface CommentService extends IService<Comment> {
    void addComment(Comment comment);

    IPage<CommentVO> getComments(Page<CommentVO> page, Group group);
}
