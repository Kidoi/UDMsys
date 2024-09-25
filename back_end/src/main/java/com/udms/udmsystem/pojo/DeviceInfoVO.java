package com.udms.udmsystem.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInfoVO {
    private Integer id;
    private String name;
    private Integer type;
    private Double longitude;
    private Double latitude;
    private Double electricity;
    private Double humidity;
    private Double wind;
    private Double temperature;
}
