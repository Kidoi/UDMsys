package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.UserMapper;
import com.udms.udmsystem.pojo.*;
import com.udms.udmsystem.service.UserService;
import com.udms.udmsystem.util.MD5;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Service("usrService")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User login(LoginForm loginForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));

        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public User register(RegisterForm registerForm) {
        User addUser = User.builder()
                .name(registerForm.getUsername())
                .password(MD5.encrypt(registerForm.getPassword()))
                .type(registerForm.getType())
                .build();
        baseMapper.insert(addUser);
        return addUser;
    }

    @Override
    public User checkUserName(RegisterForm registerForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", registerForm.getUsername());
        User user = baseMapper.selectOne(queryWrapper);
        if(null != user){
            return user;
        }
        return null;
    }

    @Override
    public void updateUserGroupById(Group group) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getGid, group.getId());
        updateWrapper.eq(User::getId, group.getLeader());
        baseMapper.update(null, updateWrapper);
    }

    @Override
    public IPage<User> getUserByGid(Page<User> pageParam, Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id, name, avatar");
        queryWrapper.eq("gid", id);
        queryWrapper.orderByAsc("id");
        Page<User> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    @Override
    public List<User> getAllUsersByGid(Integer gid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("gid", gid);
        queryWrapper.allEq(map);
        queryWrapper.select("id", "name");
        return baseMapper.selectList(queryWrapper);
    }


}
