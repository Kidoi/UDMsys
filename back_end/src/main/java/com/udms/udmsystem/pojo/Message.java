package com.udms.udmsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_message")

public class Message {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer gid;
    private Integer did;
    private String title;
    private String details;
    private Timestamp createTime;

}
