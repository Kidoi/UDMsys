package com.udms.udmsystem.pojo;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_devicecount")
@Builder
public class DeviceCount {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer gid;
    private Integer allCount;
    private Integer uvaCount;
    private Integer ugvCount;
    private Timestamp time;
}
