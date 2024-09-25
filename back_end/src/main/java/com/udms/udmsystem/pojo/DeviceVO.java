package com.udms.udmsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Array;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DeviceVO {
    private Integer id;

    private Integer type;

    private Double[] position;
}
