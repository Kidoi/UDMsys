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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("tb_pics")
public class Pic {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer gid;
    private Integer did;
    private String name;
    private Timestamp time;
    private String path;
}
