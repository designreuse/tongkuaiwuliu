package com.logistics.dao.Impl;

import com.logistics.dao.AdminDao;
import com.logistics.entity.Admin;
import org.springframework.stereotype.Repository;

/**
 * Created by Mklaus on 15/7/23.
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
    @Override
    public Class classOfT(){
        return Admin.class;
    }
}
