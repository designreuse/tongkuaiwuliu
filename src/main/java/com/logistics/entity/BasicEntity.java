package com.logistics.entity;

import javax.persistence.*;

/**
 * Created by Mklaus on 15/7/24.
 */
@MappedSuperclass
public class BasicEntity implements java.io.Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "IsDeleted")
    public boolean isDeleted;

    /**
     * The number of milliseconds since the standard base time known as "the epoch", namely January 1, 1970, 00:00:00 GMT.
     */
    @Column(name = "created_time")
    public long created_time;

    /**
     * The number of milliseconds since the standard base time known as "the epoch", namely January 1, 1970, 00:00:00 GMT.
     */
    @Column(name = "modify_time")
    public long modify_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public long getModify_time() {
        return modify_time;
    }

    public void setModify_time(long modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public int hashCode() {
        int hash = 17;

        Integer ID = this.id;

        hash = hash * 31 + ID.hashCode();

        return (hash);
    }

    /**
     * 该方法仅仅进行了最基本的辨别，如果需要精确地确定比较的对象是否相等，需要在子类中重写这个方法！
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || !this.getClass().equals(o.getClass()))
            return false;

        return this.id != ((BasicEntity) o).getId();
    }
}
