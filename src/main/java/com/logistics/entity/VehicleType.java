package com.logistics.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Mklaus on 15/8/13.
 */
@Entity
@Table(name = "VehicleType")
public class VehicleType extends BasicEntity {
    /**
     * 描述
     */
    @Column(nullable = false)
    private String description;

    /**
     * 装载量 以吨为单位
     */
    @Column
    private int capacity;

    /**
     * 耗油量，公里/升 为单位
     */
    @Column
    private int oilType;

    /**
     * 车长 厘米单位，下同
     */
    @Column
    private int length;

    /**
     * 宽
     */
    @Column
    private int width;

    /**
     * 高
     */
    @Column
    private int height;

    /**
     * 座位个数
     */
    @Column
    private int seat;

    @OneToMany(mappedBy = "vehicleType",fetch = FetchType.EAGER)
    private Set<Vehicle> vehicles;

    public VehicleType() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getOilType() {
        return oilType;
    }

    public void setOilType(int oilType) {
        this.oilType = oilType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
