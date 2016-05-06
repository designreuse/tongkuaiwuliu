package com.logistics.service.Impl;

import com.logistics.dao.*;
import com.logistics.entity.CarBusiness;
import com.logistics.entity.Vehicle;
import com.logistics.entity.VehicleRepair;
import com.logistics.service.VehicleService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle> implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private CarBusinessDao carBusinessDao;

    @Autowired
    private VehicleRepairDao vehicleRepairDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private VehicleTypeDao vehicleTypeDao;

    @Override
    public BaseDao getBaseDao() {
        return vehicleDao;
    }

    /**
     * 新增车辆
     */
    @Override
    public Integer add(Vehicle vehicle) {
        // 检测是否存在相同的发动机编号的汽车
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Vehicle.class);
        detachedCriteria.add(Restrictions.eq("identity", vehicle.getIdentity()));
        if (!get(detachedCriteria).isEmpty()) {
            return null;
        }
        return vehicleDao.save(vehicle);
    }

    /**
     * 删除单车信息
     */
    @Override
    public void delete(int id) {
        Vehicle vehicle = vehicleDao.get(id);
        // 相关的单车运营记录的外键设为null
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CarBusiness.class);
        detachedCriteria.add(Restrictions.eq("vehicle", vehicle));
        List list = carBusinessDao.search(detachedCriteria);
        for (Object carBusiness : list) {
            ((CarBusiness) carBusiness).setVehicle(null);
            carBusinessDao.update((CarBusiness) carBusiness);
        }
        // 相关的维修记录外键设置为null
        detachedCriteria = DetachedCriteria.forClass(VehicleRepair.class);
        detachedCriteria.add(Restrictions.eq("vehicle", vehicle));
        list = vehicleRepairDao.search(detachedCriteria);
        for (Object vehicleRepair : list) {
            ((VehicleRepair) vehicleRepair).setVehicle(null);
            vehicleRepairDao.update(((VehicleRepair) vehicleRepair));
        }
        vehicleDao.delete(id);
    }

    @Override
    public Integer add(Vehicle vehicle, Integer driverId, Integer vehicleTypeId) {
        if (driverId != null) {
            vehicle.setDriver(staffDao.get(driverId));
        }
        if (vehicleTypeId != null) {
            vehicle.setVehicleType(vehicleTypeDao.get(vehicleTypeId));
        }
        return add(vehicle);
    }

    @Override
    public void update(Vehicle vehicle, Integer driverId, Integer vehicleTypeId){
        if (driverId != null) {
            vehicle.setDriver(staffDao.get(driverId));
        }
        if (vehicleTypeId != null) {
            if (vehicleTypeDao.get(vehicleTypeId) != null) {
                vehicle.setVehicleType(vehicleTypeDao.get(vehicleTypeId));
            }
        }
        this.update(vehicle);
    }
}
