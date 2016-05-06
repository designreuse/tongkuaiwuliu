package com.logistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Ranger on 2015/8/10.
 * <p/>
 * 公司内部的人员信息
 */
@Entity
@Table(name = "staff")
public class Staff extends BasicEntity {
    /**
     * 真实姓名，全名，不为空
     */
    @Column(nullable = false)
    private String realName;

    /**
     * 联系方式，其实就是电话号码
     */
    @Column
    private String phoneNumber;

    /**
     * 性别，取值限制为“男”或“女”
     */
    @Column
    private String gender;

    /**
     * 身份证号码，可以唯一标识人员记录，字段值不为空
     */
    @Column(nullable = false)
    private String idCardNumber;


    /**
     * 文化程度，取值限制为：“小学以下”、“小学”、“初中”、“高中”、“专科”、
     * “技工学校”、“中技”、“中专”、“大专”、“本科”、“硕士”、“博士”
     */
    @Column
    private String levelOfEducation;

    /**
     * 政治面貌，取值限制为：“中共党员”、“中共预备党员”、“共青团员”、“民革党员”、
     * “民盟盟员”、“民建会员”、“民进会员”、“农工党党员”、“致公党党员”、
     * “九三学社社员”、“台盟盟员”、“无党派人士”、“群众”
     */
    @Column
    private String politicalGroup;

    /**
     * 职位，目前确定的职位有司机，运输管理人员，财务人员
     */
    @Column
    private String job;

    /**
     * 参加工作时间
     */
    @Column
    private Long dateOfHire;

    /**
     * 用工性质，取值限制为：“正式工”、“临时工”
     */
    @Column
    private String typeOfEmployment;

    /**
     * 职工状态，取值限制为：“休假”、“离退”、“业务中”、“等待业务”
     */
    @Column
    private String state;


    /**
     * 职工的月工资，单位为元
     */
    @Column
    private int salary;

    public Staff() {

    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public String getPoliticalGroup() {
        return politicalGroup;
    }

    public void setPoliticalGroup(String politicalGroup) {
        this.politicalGroup = politicalGroup;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTypeOfEmployment() {
        return typeOfEmployment;
    }

    public void setTypeOfEmployment(String typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Long getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(Long dateOfHire) {
        this.dateOfHire = dateOfHire;
    }
}
