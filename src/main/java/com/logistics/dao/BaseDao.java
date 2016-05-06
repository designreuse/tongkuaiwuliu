package com.logistics.dao;

import com.logistics.entity.BasicEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;

import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * Created by Mklaus on 15/7/23.
 */
@MappedSuperclass
public interface BaseDao<T extends BasicEntity> {
    Class classOfT();

    T get(int id);

    List<T> getAll();

    Integer save(T t);

    void update(T t);

    void delete(int id);

    void delete(T t);

    int count(DetachedCriteria dc);

    List<T> search(DetachedCriteria dc);

    List<T> search(DetachedCriteria dc, Integer start, Integer size);

    List<T> get(Integer start,Integer size);

    Query createQuery(String hql);

    SQLQuery createSQLQuery(String sql);
}
