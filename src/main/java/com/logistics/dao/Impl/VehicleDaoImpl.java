package com.logistics.dao.Impl;

import com.logistics.dao.VehicleDao;
import com.logistics.entity.Vehicle;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository
public class VehicleDaoImpl extends BaseDaoImpl<Vehicle> implements VehicleDao {
    @Override
    public Class classOfT() {
        return Vehicle.class;
    }
}
