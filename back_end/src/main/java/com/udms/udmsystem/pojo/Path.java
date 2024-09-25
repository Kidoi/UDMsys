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
@TableName("tb_path")
public class Path {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer did;
    private double longitude;
    private double latitude;
    private Timestamp time;
}
