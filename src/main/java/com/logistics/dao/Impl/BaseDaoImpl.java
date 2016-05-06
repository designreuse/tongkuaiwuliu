package com.logistics.dao.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.entity.BasicEntity;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * Created by Mklaus on 15/7/23.
 */
@MappedSuperclass
public abstract class BaseDaoImpl<T extends BasicEntity> implements BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T get(int id) {
        return (T) getSession().get(classOfT(), id);
    }

    @Override
    public List<T> getAll() {
        DetachedCriteria dc = DetachedCriteria.forClass(classOfT());
        return search(dc);
    }

    @Override
    public Integer save(T t) {
        t.setCreated_time(System.currentTimeMillis());
        t.setModify_time(System.currentTimeMillis());
        return (Integer) getSession().save(t);
    }

    @Override
    public void update(T t) {
        t.setModify_time(System.currentTimeMillis());
        getSession().update(t);
    }

    @Override
    public void delete(int id) {
        getSession().delete(get(id));
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public int count(DetachedCriteria dc) {
        Criteria c = dc.getExecutableCriteria(getSession()).setProjection(Projections.rowCount());
        return (Integer) c.uniqueResult();
    }

    @Override
    public List search(DetachedCriteria dc) {
        return search(dc, null, null);
    }

    @Override
    public List search(DetachedCriteria dc, Integer start, Integer size) {
        dc.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        Criteria c = dc.getExecutableCriteria(getSession());
        if (start != null) {
            c.setFirstResult(start);
        }

        if (size != null) {
            c.setMaxResults(size);
        }

        return c.list();
    }

    @Override
    public List<T> get(Integer start, Integer size) {
        String className = classOfT().getName().substring(classOfT().getName().lastIndexOf(".") + 1);

        List<T> l = createQuery("from " + className)
                .setFirstResult(start)
                .setMaxResults(size)
                .list();

        return l;
    }

    @Override
    public Query createQuery(String hql) {
        return getSession().createQuery(hql);
    }

    @Override
    public SQLQuery createSQLQuery(String sql) {
        return getSession().createSQLQuery(sql);
    }
}
