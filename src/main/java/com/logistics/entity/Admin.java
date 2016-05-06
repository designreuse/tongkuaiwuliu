package com.logistics.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Mklaus on 15/7/23.
 * <p/>
 * 管理员信息表
 */
@Entity
@Table(name = "admin")
public class Admin extends BasicEntity {

    @Column(name = "adminName")
    private String adminName;

    @Column(name = "salt", length = 8)
    private byte[] salt;

    @Column(name = "password")
    private byte[] password;

    /**
     * 管理员的类型有：
     * 1、超级管理员，具有所有表CRUD的权限；
     * 2、物资管理员，具有对物资信息表CURD的权限；
     * 3、运输管理员，具有对货物，地址，单车运营记录，承运商业务记录,运输任务CRUD的权限；
     * 4、车辆管理员，具有对车辆信息CRUD的权限；
     * 5、人力资源管理员，具有对雇员信息CRUD的权限（人员信息表还没有做）；
     * 6、公司信息管理员，具有对公司信息CRUD的权限；
     * 7、财务管理员，具有对加油记录CRUD的权限。
     */
    @Column
    private int type;


    public Admin() {
    }

    public Admin(String adminName, byte[] password) {
        this.adminName = adminName;
        this.password = password;
    }

    public Admin(String adminName,byte[] password, int type) {
        this.password = password;
        this.type = type;
        this.adminName = adminName;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
