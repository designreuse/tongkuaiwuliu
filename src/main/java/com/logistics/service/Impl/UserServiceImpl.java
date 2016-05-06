package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.TransportTaskDao;
import com.logistics.dao.UserDao;
import com.logistics.entity.TransportTask;
import com.logistics.entity.User;
import com.logistics.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TransportTaskDao transportTaskDao;

    @Override
    public BaseDao getBaseDao() {
        return userDao;
    }

    /**
     * 用户登录
     */
    @Override
    public User login(User user) {
        // 检测用户是否用邮箱登录
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("email", user.getEmail()));
        detachedCriteria.add(Restrictions.eq("password", user.getPassword()));
        List<User> list = userDao.search(detachedCriteria);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        // 检测用户是否用电话号码登录
        detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("phone", user.getPhone()));
        detachedCriteria.add(Restrictions.eq("password", user.getPassword()));
        list = userDao.search(detachedCriteria);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 注册用户
     */
    @Override
    public User register(User user) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("email", user.getEmail()));
        if (!userDao.search(detachedCriteria).isEmpty()) { // 存在相同Email的用户，不允许注册
            return null;
        }
        detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("phone", user.getPhone()));
        if (!userDao.search(detachedCriteria).isEmpty()) { // 存在相同电话号码的用户，不允许注册
            return null;
        }
        userDao.save(user);
        return user;
    }

    @Override
    public Integer add(User user) {
        return register(user).getId();
    }

    /**
     * 删除用户
     *
     * @param id 需要删除的用户的id
     */
    @Override
    public void delete(int id) {
        // 将相关任务单的外键设置为null
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TransportTask.class);
        detachedCriteria.add(Restrictions.eq("customer", userDao.get(id)));
        List<TransportTask> transportTaskList = transportTaskDao.search(detachedCriteria);
        for (TransportTask transportTask : transportTaskList) {
            transportTask.setCustomer(null);
            transportTaskDao.update(transportTask);
        }
        super.delete(id);
    }

    @Override
    public boolean passwd(int userId, String oldPass, String newPass) {
        User u = userDao.get(userId);
        if (u.getPassword().equals(oldPass)) {
            u.setPassword(newPass);
            userDao.update(u);
            return true;
        }
        return false;
    }
}
