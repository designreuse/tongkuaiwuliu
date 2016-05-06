package com.logistics.service.Impl;

import com.logistics.entity.BasicEntity;
import com.logistics.service.BaseService;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by Mklaus on 15/8/11.
 */
public abstract class BaseServiceImpl<T extends BasicEntity> implements BaseService<T> {
    @Override
    public Integer add(T t) {
        return getBaseDao().save(t);
    }

    @Override
    public void delete(int id) {
        getBaseDao().delete(id);
    }

    @Override
    public void delete(T t) {
        getBaseDao().delete(t);
    }

    @Override
    public void update(T t) {
        BasicEntity temp = getBaseDao().get(t.id);
        t.setIsDeleted(temp.isDeleted);
        t.setCreated_time(temp.getCreated_time());
        getBaseDao().update(t);
    }

    @Override
    public T get(int id) {
        return (T) getBaseDao().get(id);
    }

    @Override
    public List<T> getAll() {
        return getBaseDao().getAll();
    }

    @Override
    public List<T> get(DetachedCriteria dc) {
        return getBaseDao().search(dc);
    }

    @Override
    public List<T> get(Integer start, Integer size) {
        return getBaseDao().get(start,size);
    }

    @Override
    public List<T> get(DetachedCriteria dc, Integer start, Integer size) {
        return getBaseDao().search(dc, start, size);
    }
}
