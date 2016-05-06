package com.logistics.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ranger on 2015/8/10.
 * <p>
 * 地址信息
 */
@Entity
@Table(name = "address")
public class Address extends BasicEntity {
    /**
     * 地域名，省、市、县、镇名称
     */
    @Column(name = "AreaName", length = 64)
    private String areaName;

    /**
     * 代表地域级别，我国的地域级别划分如下：
     * （一）全国分为省、自治区、直辖市；
     * （二）省、自治区分为自治州、县、自治县、市；
     * （三）县、自治县分为乡、民族乡、镇。
     * 直辖市和较大的市分为区、县。自治州分为县、自治县、市。
     * 编码可以采用字符串表示地域级别，比如：“省”。
     * <p>
     * 1 = 省，2 = 市，3 = 县
     */
    @Column(name = "Level")
    private int level;

    /**
     * 地名拼音简称，例如痛快简称为PZH
     */
    @Column(name = "Pinyin", length = 32)
    private String pinyin;

    @ManyToOne
    @JoinColumn(name = "parent")
    private Address parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Address> children;

    public Address() {
    }

    public Address(String areaName, int level) {
        this.areaName = areaName;
        this.level = level;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Address getParent() {
        return parent;
    }

    public void setParent(Address parent) {
        this.parent = parent;
    }

    public Set<Address> getChildren() {
        return children;
    }

    public void setChildren(Set<Address> children) {
        this.children = children;
    }
}
