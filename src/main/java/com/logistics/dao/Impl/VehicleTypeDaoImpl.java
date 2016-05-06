package com.logistics.dao.Impl;

import com.logistics.dao.VehicleTypeDao;
import com.logistics.entity.VehicleType;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository
public class VehicleTypeDaoImpl extends BaseDaoImpl<VehicleType> implements VehicleTypeDao {
    @Override
    public Class classOfT() {
        return VehicleType.class;
    }
}
