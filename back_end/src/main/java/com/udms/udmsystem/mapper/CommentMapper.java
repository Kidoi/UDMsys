package com.udms.udmsystem.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.udms.udmsystem.pojo.Comment;
import com.udms.udmsystem.pojo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    IPage<CommentVO> getComments(Page<CommentVO> page, @Param(Constants.WRAPPER) Wrapper<CommentVO> wrapper);
}
