package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.VehicleRepairDao;
import com.logistics.entity.VehicleRepair;
import com.logistics.service.VehicleRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service
public class VehicleRepairServiceImpl extends BaseServiceImpl<VehicleRepair> implements VehicleRepairService {
    @Autowired
    private VehicleRepairDao vehicleRepairDao;

    @Override
    public BaseDao getBaseDao() {
        return vehicleRepairDao;
    }
}
