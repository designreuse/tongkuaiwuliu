package com.logistics.entity;

import javax.persistence.*;

/**
 * Created by Ranger on 2015/8/10.
 * <p/>
 * <p>承运商承运信息表</p>
 */
@Entity
@Table(name = "carrierBusiness")
public class CarrierBusiness extends BasicEntity {

    @OneToOne
    @JoinColumn(name = "taskId")
    private TransportTask task;

    @OneToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @Column
    private boolean isPayed;

    public CarrierBusiness() {

    }

    public CarrierBusiness(TransportTask task) {
        this.task = task;
    }

    public TransportTask getTask() {
        return task;
    }

    public void setTask(TransportTask task) {
        this.task = task;
    }

    public boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
