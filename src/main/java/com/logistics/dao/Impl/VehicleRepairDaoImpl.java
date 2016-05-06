package com.logistics.dao.Impl;

import com.logistics.dao.VehicleRepairDao;
import com.logistics.entity.VehicleRepair;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository
public class VehicleRepairDaoImpl extends BaseDaoImpl<VehicleRepair> implements VehicleRepairDao {
    @Override
    public Class classOfT() {
        return VehicleRepair.class;
    }
}
