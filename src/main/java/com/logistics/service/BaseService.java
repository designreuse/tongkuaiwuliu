package com.logistics.service;

import com.logistics.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Mklaus on 15/8/11.
 */
public interface BaseService<T> {
    BaseDao getBaseDao();

    Integer add(T t);

    void delete(int id);

    void delete(T t);

    void update(T t);

    T get(int id);

    List<T> getAll();

    List<T> get(DetachedCriteria dc);

    List<T> get(Integer start,Integer size);

    List<T> get(DetachedCriteria dc, Integer start, Integer size);
}
