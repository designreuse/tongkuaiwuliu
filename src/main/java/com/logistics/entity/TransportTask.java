package com.logistics.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Mklaus on 15/8/13.
 */
@Entity
@Table(name = "transportTask")
public class TransportTask extends BasicEntity {
    @Column
    private String title;

    /**
     * 起运地，字段值不能为空
     */
    @Column(nullable = false)
    private String startPlace;

    /**
     * 止运地，字段不能为空
     */
    @Column(nullable = false)
    private String endPlace;

    @Column
    private long startDate;

    @Column
    private long endDate;

    /**
     * 自营 0 ,别人承运 1
     */
    @Column
    private int type;

    /**
     * 当type = 1时，该字段有效，否则弃用
     */
    @Column
    private double rate;

    /**
     * 申请 0, 进行 1，完成 2
     */
    @Column
    private int state;

    /**
     * 总价格
     */
    @Column
    private int sumPrice;

    /**
     * 给顾客查询订单的ID号
     */
    @Column(nullable = false)
    private String queryId;

    /**
     * 委托人
     */
    @OneToOne
    @JoinColumn(name = "customer")
    private User customer;

    /**
     * 单车组
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "task")
    private Set<CarBusiness> carBusinesses;

    /**
     * 货物组
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "task")
    private Set<Cargo> cargoSet;

    public TransportTask() {
        this.state = 0;
    }

    public TransportTask(String title, String startPlace, String endPlace, long startDate, long endDate,
                         int type, double rate, User customer, int sumPrice) {
        this();
        this.title = title;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.rate = rate;
        this.customer = customer;
        this.sumPrice = sumPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Set<Cargo> getCargoSet() {
        return cargoSet;
    }

    public void setCargoSet(Set<Cargo> cargoSet) {
        this.cargoSet = cargoSet;
    }

    public Set<CarBusiness> getCarBusinesses() {
        return carBusinesses;
    }

    public void setCarBusinesses(Set<CarBusiness> carBusinesses) {
        this.carBusinesses = carBusinesses;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }
}
