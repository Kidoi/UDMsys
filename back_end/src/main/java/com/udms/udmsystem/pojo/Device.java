package com.udms.udmsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_device")
@Builder

public class Device {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer type;
    private Integer gid;
    private Double longitude;
    private Double latitude;
    private Integer status;
    private Double electricity;
    private Double humidity;
    private Double wind;
    private Double temperature;
}
