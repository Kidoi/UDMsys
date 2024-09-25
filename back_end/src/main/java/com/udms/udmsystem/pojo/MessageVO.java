package com.udms.udmsystem.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageVO {
    private Integer id;
    private String title;
    private String details;
    private Integer status;
    private Timestamp createTime;
}
