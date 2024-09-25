package com.udms.udmsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.udms.udmsystem.pojo.Group;
import com.udms.udmsystem.pojo.User;
import com.udms.udmsystem.service.GroupService;
import com.udms.udmsystem.service.UserService;
import com.udms.udmsystem.util.Result;
import com.udms.udmsystem.util.inviteCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @GetMapping("/getGroupInfo/{id}")
    public Result getGroupInfo(
            @PathVariable("id") Integer id
    ){
        Group group = groupService.getGroupInfoById(id);
        return Result.ok(group);
    }

    @PostMapping("/createGroup")
    public Result createGroup(
            @RequestBody Group group
    ){
        Group g = groupService.createGroup(group);
        // 修改leader的gid
        userService.updateUserGroupById(g);
        return Result.ok();
    }

    @PostMapping("/updateGroup")
    public Result updateGroup(@RequestBody Group group){
        groupService.saveOrUpdate(group);
        return Result.ok();
    }

    @GetMapping("/getInviteCode/{gid}")
    public Result getInviteCode(
            @PathVariable("gid") Integer gid
    ){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", inviteCode.toSerialCode(gid));
        return Result.ok(map);
    }

    @GetMapping("/validateInviteCode/{code}")
    public Result validateInviteCode(
            @PathVariable("code") String code
    ){
        Long gid = inviteCode.codeToId(code);
        Group group = groupService.getGroupInfoById(Math.toIntExact(gid));
        if(null != group){
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", group.getId());
            return Result.ok(map);
        }
        return Result.fail().message("邀请码无效！");
    }

    @GetMapping("/getGroupMember/{pageNo}/{pageSize}")
    public Result getGroupMember(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            Group group
    ){
        Page<User> page = new Page<>(pageNo, pageSize);
        IPage<User> pageRs = userService.getUserByGid(page, group.getId());
        return Result.ok(pageRs);
    }

    @GetMapping("/getAllGroupMembers/{gid}")
    public Result getAllGroupMembers(
        @PathVariable("gid") Integer gid
    ){
        List<User> users = userService.getAllUsersByGid(gid);
        return Result.ok(users);
    }

}
