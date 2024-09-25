package com.udms.udmsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.udms.udmsystem.mapper.GroupMapper;
import com.udms.udmsystem.pojo.Group;
import com.udms.udmsystem.service.GroupService;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Builder
@Service("groService")
@Transactional
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Override
    public Group getGroupInfoById(Integer id) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Group createGroup(Group group) {
        Group addGroup = Group.builder()
                .name(group.getName())
                .leader(group.getLeader())
                .introduction(group.getIntroduction())
                .build();
        baseMapper.insert(addGroup);

        return addGroup;
    }
}
