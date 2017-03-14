package com.example.Entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Xin09 on 2017/2/14.
 */
@Data
public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer sign;

    private Integer integral;

    private Integer con_sign;

    private Date sign_time;

}
