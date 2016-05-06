package com.logistics.entity;

import javax.persistence.*;

/**
 * Created by Mklaus on 15/8/13.
 */
@Entity
@Table(name = "repair")
public class VehicleRepair extends BasicEntity {

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "goodsRecord_id")
    private GoodsRecord goodsRecord;

    @Column
    private String reasonDesc;

    @OneToOne
    @JoinColumn(name = "repairman")
    private Staff repairman;

    public VehicleRepair() {
    }

    public VehicleRepair(Vehicle vehicle, Staff repairman) {
        this.vehicle = vehicle;
        this.repairman = repairman;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public GoodsRecord getGoodsRecord() {
        return goodsRecord;
    }

    public void setGoodsRecord(GoodsRecord goodsRecord) {
        this.goodsRecord = goodsRecord;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public Staff getRepairman() {
        return repairman;
    }

    public void setRepairman(Staff repairman) {
        this.repairman = repairman;
    }
}
