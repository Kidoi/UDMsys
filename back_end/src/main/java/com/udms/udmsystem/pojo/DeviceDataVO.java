package com.udms.udmsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDataVO {
    private Integer gid;
    private double humidity;
    private double wind;
    private double temperature;
}
