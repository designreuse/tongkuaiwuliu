package com.logistics.dao.Impl;

import com.logistics.dao.CarBusinessDao;
import com.logistics.entity.CarBusiness;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository("carBusinessDaoImpl")
public class CarBusinessDaoImpl extends BaseDaoImpl<CarBusiness> implements CarBusinessDao {
    @Override
    public Class classOfT() {
        return CarBusiness.class;
    }
}
