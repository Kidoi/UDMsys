package com.udms.udmsystem.pojo;

import lombok.Data;

@Data
public class RegisterForm {
    private String username;
    private String password;
    private Integer type;
}
