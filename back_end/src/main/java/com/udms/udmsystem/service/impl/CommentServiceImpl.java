package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.CommentMapper;
import com.udms.udmsystem.pojo.Comment;
import com.udms.udmsystem.pojo.CommentVO;
import com.udms.udmsystem.pojo.Group;
import com.udms.udmsystem.service.CommentService;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Builder
@Service("comService")
@Transactional
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public void addComment(Comment comment) {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        Comment addComment = Comment.builder()
                .uid(comment.getUid())
                .gid(comment.getGid())
                .time(timestamp)
                .details(comment.getDetails())
                .build();
        baseMapper.insert(addComment);
    }

    @Resource
    private CommentMapper commentMapper;

    @Override
    public IPage<CommentVO> getComments(Page<CommentVO> page, Group group) {
        QueryWrapper<CommentVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tb_comments.gid", group.getId());
        queryWrapper.orderByDesc("time");
        return commentMapper.getComments(page, queryWrapper);
    }
}
