package com.logistics.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Mklaus on 15/7/24.
 * <p/>
 * 物资基本信息
 */
@Entity
@Table(name = "goods")
public class Goods extends BasicEntity {
//    @ManyToOne
//    private GoodsRecord goodsRecord;

    @Column(name = "type")
    private String type;

    @Column
    private String goodsName;

    @Column
    private String description;

    /**
     * 以分为单位
     */
    @Column
    private int price;

    /**
     * 剩余数量
     */
    @Column
    private int count;

    @Column
    private String weight;

    private int sumPrice;

    public Goods() {
    }

    public Goods(String goodsName, String type) {
        this.goodsName = goodsName;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSumPrice() {
        return this.price * this.count;
    }

}
