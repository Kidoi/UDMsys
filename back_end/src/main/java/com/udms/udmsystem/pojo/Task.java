package com.udms.udmsystem.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_task")
public class Task {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer gid;
    private Integer status;
    private String mlist;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createTime;
    private String details;
    private String title;
}
