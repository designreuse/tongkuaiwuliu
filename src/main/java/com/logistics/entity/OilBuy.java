package com.logistics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Mklaus on 15/8/21.
 */
@Entity
@Table(name = "oilBuy")
public class OilBuy extends BasicEntity {
    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Staff staff;

    @Column
    private double litre;

    @Column
    private double price;

    public OilBuy() {
    }

    public OilBuy(Vehicle vehicle, Staff staff) {
        this.vehicle = vehicle;
        this.staff = staff;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getLitre() {
        return litre;
    }

    public void setLitre(double litre) {
        this.litre = litre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSumPrice() {
        return (this.price * this.litre);
    }
}
