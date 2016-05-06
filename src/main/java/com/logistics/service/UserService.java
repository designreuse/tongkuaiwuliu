package com.logistics.service;

import com.logistics.entity.User;

/**
 * Created by Ranger on 2015/8/14.
 */
public interface UserService extends BaseService<User> {
    User login(User user);

    User register(User user);

    public boolean passwd(int userId, String oldPass,String newPass);
}
