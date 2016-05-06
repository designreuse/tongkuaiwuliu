package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.CargoDao;
import com.logistics.dao.TransportTaskDao;
import com.logistics.entity.Cargo;
import com.logistics.service.CargoService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service
public class CargoServiceImpl extends BaseServiceImpl<Cargo> implements CargoService {
    @Autowired
    private CargoDao cargoDao;
    @Autowired
    private TransportTaskDao transportTaskDao;

    @Override
    public BaseDao getBaseDao() {
        return cargoDao;
    }

    @Override
    public Integer add(Cargo cargo, Integer taskId) {
        // 检测货物名称和货物重量
        if (cargo.getWeight() <= 0) {
            return null;
        }
        cargo.setTask(transportTaskDao.get(taskId));
        return cargoDao.save(cargo);
    }

    @Override
    public void update(Cargo cargo, Integer taskId) {
        // 检测货物名称和货物重量
        if (cargo.getWeight() > 0) {
            cargo.setTask(transportTaskDao.get(taskId));
            cargoDao.update(cargo);
        }
    }

    @Override
    public List<Cargo> getCargoByTaskId(int taskId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Cargo.class);
        detachedCriteria.add(Restrictions.eq("task", transportTaskDao.get(taskId)));
        return cargoDao.search(detachedCriteria);
    }
}
