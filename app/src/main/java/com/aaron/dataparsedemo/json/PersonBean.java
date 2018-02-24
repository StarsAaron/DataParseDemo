package com.aaron.dataparsedemo.json;

import java.io.Serializable;

/**
 * Created by Aaron on 2016/6/2.
 */
public class PersonBean implements Serializable {
    private String name;
    private String sex;
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "name:" + name + ",sex:" + sex + ",addr:" + addr;
    }
}
