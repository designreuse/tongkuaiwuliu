package com.logistics.dao.Impl;

import com.logistics.dao.StaffDao;
import com.logistics.entity.Staff;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    @Override
    public Class classOfT() {
        return Staff.class;
    }
}
