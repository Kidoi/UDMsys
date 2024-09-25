package com.udms.udmsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceCountVO {
    private Integer allCount;
    private Integer onlineAllCount;
    private Integer uvaCount;
    private Integer onlineUvaCount;
    private Integer ugvCount;
    private Integer onlineUgvCount;
}
