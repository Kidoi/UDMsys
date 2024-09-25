package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.*;

import java.util.List;

public interface UserService extends IService<User> {
    User login(LoginForm loginForm);
    User getUserById(Long userId);

    User register(RegisterForm registerForm);

    User checkUserName(RegisterForm registerForm);

    void updateUserGroupById(Group group);

    IPage<User> getUserByGid(Page<User> pageParam, Integer id);

    List<User> getAllUsersByGid(Integer gid);
}
