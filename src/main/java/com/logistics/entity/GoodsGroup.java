package com.logistics.entity;

import javax.persistence.*;

/**
 * Created by Mklaus on 15/8/20.
 */
@Entity
@Table(name = "goodsGroup")
public class GoodsGroup extends BasicEntity{
    @OneToOne(fetch = FetchType.EAGER)
    private Goods goods;

    @ManyToOne
    private GoodsRecord goodsRecord;

    @Column
    private int count;

    public GoodsGroup() {
    }

    public GoodsGroup(Goods goods, int count) {
        this.goods = goods;
        this.count = count;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GoodsRecord getGoodsRecord() {
        return goodsRecord;
    }

    public void setGoodsRecord(GoodsRecord goodsRecord) {
        this.goodsRecord = goodsRecord;
    }
}
