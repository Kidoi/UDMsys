package com.udms.udmsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("tb_comments")
public class CommentVO {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer gid;
    private Timestamp time;
    private String details;

    @TableField(exist = false)
    private String author;
    @TableField(exist = false)
    private String avatar;
}
