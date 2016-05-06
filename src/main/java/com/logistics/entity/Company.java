package com.logistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ranger on 2015/8/10.
 * <p/>
 * 公司基本信息
 */
@Entity
@Table(name = "company")
public class Company extends BasicEntity {
    /**
     * 公司单位名称
     */
    @Column
    private String companyName;

    /**
     * 联系方式，就是电话号码
     */
    @Column
    private String phoneNumber;

    /**
     * 法人代表姓名
     */
    @Column
    private String corporation;

    /**
     * 公司简介
     */
    @Column
    private String introduction;

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
