package com.udms.udmsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.udms.udmsystem.pojo.Comment;
import com.udms.udmsystem.pojo.CommentVO;
import com.udms.udmsystem.pojo.Group;
import com.udms.udmsystem.service.CommentService;
import com.udms.udmsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public Result addComment(
            @RequestBody Comment comment
    ){
        commentService.addComment(comment);
        return Result.ok();
    }

    @GetMapping("/getComment/{pageNo}/{pageSize}")
    public Result getComment(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            Group group
    ){
        Page<CommentVO> page = new Page<>(pageNo, pageSize);
        IPage<CommentVO> pageRs = commentService.getComments(page, group);
        return Result.ok(pageRs);
    }

}
