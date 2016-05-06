package com.logistics.entity;

import javax.persistence.*;

/**
 * Created by Ranger on 2015/8/10.
 * <p/>
 * 车辆,驾驶员运营信息表
 */
@Entity
@Table(name = "carBusiness")
public class CarBusiness extends BasicEntity {
    /**
     * 开始日期
     */
    @Column(name = "DateOfStarting")
    private long dateOfStarting;

    /**
     * 结束日期
     */
    @Column(name = "DateOfFinishing")
    private long dateOfFinishing;

    /**
     * 一个单车运营记录对应一辆车，一个司机，一个运输任务
     */
    @ManyToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private TransportTask task;

    public CarBusiness() {

    }

    public CarBusiness(Vehicle vehicle, TransportTask task) {
        this.vehicle = vehicle;
        this.task = task;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public TransportTask getTask() {
        return task;
    }

    public void setTask(TransportTask task) {
        this.task = task;
    }

    public long getDateOfFinishing() {
        return dateOfFinishing;
    }

    public void setDateOfFinishing(long dateOfFinishing) {
        this.dateOfFinishing = dateOfFinishing;
    }

    public long getDateOfStarting() {
        return dateOfStarting;
    }

    public void setDateOfStarting(long dateOfStarting) {
        this.dateOfStarting = dateOfStarting;
    }
}
