package com.udms.udmsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.udms.udmsystem.pojo.User;
import com.udms.udmsystem.service.DeviceService;
import com.udms.udmsystem.service.UserService;
import com.udms.udmsystem.util.JwtHelper;
import com.udms.udmsystem.util.MD5;
import com.udms.udmsystem.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/updatePsw/{oldPsw}/{newPsw}")
    public Result updatePsw(
            @RequestHeader("Authorization") String token,
            @PathVariable("oldPsw") String oldPsw,
            @PathVariable("newPsw") String newPsw
    ){
        boolean expiration = JwtHelper.isExpiration(token);
        if(expiration){
            return Result.fail().message("token失效，请重新登录后再修改密码。");
        }
        Long userId = JwtHelper.getUserId(token);

        oldPsw = MD5.encrypt(oldPsw);
        newPsw = MD5.encrypt(newPsw);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId.intValue());
        queryWrapper.eq("password", oldPsw);
        User user = userService.getOne(queryWrapper);
        if(user != null){
            user.setPassword(newPsw);
            userService.saveOrUpdate(user);
        }else{
            return Result.fail().message("原密码有误！");
        }

        return Result.ok();
    }


    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody User user){
        userService.saveOrUpdate(user);
        return Result.ok();
    }
}
