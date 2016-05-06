package com.logistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Mklaus on 15/8/13.
 * <p/>
 * 客户信息表
 */
@Entity
@Table(name = "user")
public class User extends BasicEntity {
    @Column
    private String name;

    /**
     * 用户的电话，可以用于注册帐号
     */
    @Column
    private String phone;

    /**
     * 用户的住址
     */
    @Column
    private String address;

    /**
     * 用户的Email，可以用于注册帐号
     */
    @Column
    private String email;

    /**
     * 用户的密码，不能为空
     */
    @Column(nullable = false)
    private String password;

    public User() {
    }

    /**
     * 登录以 email 为帐号
     *
     * @param email email
     * @param password 密码
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
