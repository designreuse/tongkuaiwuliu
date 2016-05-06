package com.logistics.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ranger on 2015/8/10.
 * <p/>
 * 汽车详情表
 */
@Entity
@Table(name = "vehicle")
public class Vehicle extends BasicEntity {
    /**
     * 车牌号
     */
    @Column
    private String carNumber;
    /**
     * 发动机编号，可以唯一确定车辆，插入时该字段不能为空
     */
    @Column(nullable = false)
    private String identity;

    /**
     * 车身颜色
     */
    @Column
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicleType")
    private VehicleType vehicleType;

    @OneToOne
    @JoinColumn(name = "driver")
    private Staff driver;

    @OneToMany(mappedBy = "vehicle",fetch = FetchType.EAGER)
    private Set<CarBusiness> carBusinessRecords;

    public Vehicle() {

    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Staff getDriver() {
        return driver;
    }

    public void setDriver(Staff driver) {
        this.driver = driver;
    }

    public Set<CarBusiness> getCarBusinessRecords() {
        return carBusinessRecords;
    }

    public void setCarBusinessRecords(Set<CarBusiness> carBusinessRecords) {
        this.carBusinessRecords = carBusinessRecords;
    }
}
