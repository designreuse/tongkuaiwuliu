package com.logistics.dao.Impl;

import com.logistics.dao.UserDao;
import com.logistics.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
    @Override
    public Class classOfT() {
        return User.class;
    }
}
