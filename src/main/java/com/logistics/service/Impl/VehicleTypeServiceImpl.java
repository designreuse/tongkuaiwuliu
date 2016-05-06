package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.VehicleDao;
import com.logistics.dao.VehicleTypeDao;
import com.logistics.entity.Vehicle;
import com.logistics.entity.VehicleType;
import com.logistics.service.VehicleTypeService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * Created by hwen on 15/8/23.
 */

@Service
public class VehicleTypeServiceImpl extends BaseServiceImpl<VehicleType> implements VehicleTypeService {
    @Autowired
    private VehicleTypeDao vehicleTypeDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public BaseDao getBaseDao() {
        return vehicleTypeDao;
    }

    @Override
    public Integer add(VehicleType vehicleType) {
        // 查看数据库中是否有相同的车型描述
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VehicleType.class);
        detachedCriteria.add(Restrictions.eq("description", vehicleType.getDescription()));
        if (!vehicleTypeDao.search(detachedCriteria).isEmpty()) {
            return null;
        }
        return super.add(vehicleType);
    }

    @Override
    public void delete(int id) {
        // 删除车型数据时，将汽车表引用车型表的外键设置为null
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Vehicle.class);
        detachedCriteria.add(Restrictions.eq("vehicleType", vehicleTypeDao.get(id)));
        List<Vehicle> vehicleList = vehicleDao.search(detachedCriteria);
        for (Vehicle vehicle : vehicleList) {
            vehicle.setVehicleType(null);
            vehicleDao.update(vehicle);
        }
        this.delete(id);
    }
}
