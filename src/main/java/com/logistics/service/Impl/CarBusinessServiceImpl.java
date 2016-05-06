package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.CarBusinessDao;
import com.logistics.dao.TransportTaskDao;
import com.logistics.dao.VehicleDao;
import com.logistics.entity.CarBusiness;
import com.logistics.service.CarBusinessService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service("carBusinessServiceImpl")
public class CarBusinessServiceImpl extends BaseServiceImpl<CarBusiness> implements CarBusinessService {
    @Autowired
    private CarBusinessDao carBusinessDao;
    @Autowired
    private TransportTaskDao transportTaskDao;
    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public BaseDao getBaseDao() {
        return carBusinessDao;
    }

    @Override
    public List<CarBusiness> getCarBusinessByTaskId(int taskId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CarBusiness.class);
        detachedCriteria.add(Restrictions.eq("task", transportTaskDao.get(taskId)));
        return carBusinessDao.search(detachedCriteria);
    }

    @Override
    public Integer add(Integer taskId, Integer vehicleId, CarBusiness carBusiness) {
        if (taskId != null) {
            carBusiness.setTask(transportTaskDao.get(taskId));
        }
        if (vehicleId != null) {
            carBusiness.setVehicle(vehicleDao.get(vehicleId));
        }
        return carBusinessDao.save(carBusiness);
    }

    @Override
    public void update(Integer taskId, Integer vehicleId, CarBusiness carBusiness) {
        if (vehicleId != null) {
            carBusiness.setVehicle(vehicleDao.get(vehicleId));
        }
        if (taskId != null) {
            carBusiness.setTask(transportTaskDao.get(taskId));
        }
        update(carBusiness);
    }
}
