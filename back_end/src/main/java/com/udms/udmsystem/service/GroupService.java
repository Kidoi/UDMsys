package com.udms.udmsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.udms.udmsystem.pojo.Group;

public interface GroupService extends IService<Group> {
    Group getGroupInfoById(Integer id);

    Group createGroup(Group group);
}
