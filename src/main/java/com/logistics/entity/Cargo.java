package com.logistics.entity;

import javax.persistence.*;

/**
 * Created by Mklaus on 15/8/13.
 */
@Entity
@Table(name = "cargo")
public class Cargo extends BasicEntity {
    @Column(nullable = false)
    private String name;

    /**
     * 重量 (吨)
     */
    @Column(nullable = false)
    private int weight;

    /**
     * 单价 (分)
     */
    @Column
    private int price;

    @Column
    private int count;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private TransportTask task;

    public Cargo() {
    }

    public Cargo(int price, String name, int weight) {
        this.price = price;
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public TransportTask getTask() {
        return task;
    }

    public void setTask(TransportTask task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        } else {
            Cargo castObj = (Cargo) o;
            if (!castObj.getName().equals(this.getName()))
                return false;
            if (!(castObj.getTask().getId() == task.getId()))
                return false;
        }
        return true;
    }
}
