package com.logistics.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mklaus on 15/8/13.
 * <p/>
 * 物资信息记录
 */
@Entity
@Table(name = "goodsRecord")
public class GoodsRecord extends BasicEntity {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "goodsRecord")
    private Set<GoodsGroup> goodsGroupSet = new HashSet<GoodsGroup>(8);

    /**
     * 0=使用，1=添加购买
     */
    @Column
    private int type;

    /**
     * 相关负责人
     */
    @OneToOne
    @JoinColumn(name = "charger")
    private Staff charger;

    /**
     * 用途
     */
    @Column
    private String purpose;

    @Column
    private int sumPrice;

    public GoodsRecord() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Staff getCharger() {
        return charger;
    }

    public void setCharger(Staff charger) {
        this.charger = charger;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Set<GoodsGroup> getGoodsGroupSet() {
        return goodsGroupSet;
    }

    public void setGoodsGroupSet(Set<GoodsGroup> goodsGroupSet) {
        this.goodsGroupSet = goodsGroupSet;
    }
}
