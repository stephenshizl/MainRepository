package com.example.a61555.ormlitedemo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 61555 on 2017/8/3.
 * User Table Bean
 */
@DatabaseTable(tableName = "user_info")
public class UserInfo {
    @DatabaseField(generatedId = true, columnName = "id")
    private int userId;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "addr")
    private String addr;
    @DatabaseField(columnName = "sex")
    private String sex;

    //需要提供一个空构造函数给 ORMLite 框架
    public UserInfo() {
    }

    public UserInfo(String name, String addr, String sex) {
        this.name = name;
        this.addr = addr;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
