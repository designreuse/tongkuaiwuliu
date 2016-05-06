package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.StaffDao;
import com.logistics.dao.VehicleDao;
import com.logistics.dao.VehicleRepairDao;
import com.logistics.entity.Staff;
import com.logistics.entity.Vehicle;
import com.logistics.entity.VehicleRepair;
import com.logistics.service.StaffService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private VehicleRepairDao vehicleRepairDao;

    @Override
    public BaseDao getBaseDao() {
        return staffDao;
    }

    @Override
    public void delete(int id) {
        Staff staff = staffDao.get(id);
        // 将相关的汽车记录的外键设置为空
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Vehicle.class);
        detachedCriteria.add(Restrictions.eq("driver", staff));
        List list = vehicleDao.search(detachedCriteria);
        for (Object vehicle : list) {
            ((Vehicle) vehicle).setDriver(null);
            vehicleDao.update((Vehicle) vehicle);
        }
        // 将相关的维修记录的外键设置为空
        detachedCriteria = DetachedCriteria.forClass(VehicleRepair.class);
        detachedCriteria.add(Restrictions.eq("repairman", staff));
        list = vehicleRepairDao.search(detachedCriteria);
        for (Object vehicleRepair : list) {
            ((VehicleRepair) vehicleRepair).setRepairman(null);
            vehicleRepairDao.update((VehicleRepair) vehicleRepair);
        }
        staffDao.delete(id);
    }

    @Override
    public List<Staff> getStaffList(String job) {
        return getStaffList(job, null, null);
    }

    @Override
    public List<Staff> getStaffList(String job, Integer start, Integer size) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("job", job));
        return staffDao.search(detachedCriteria, start, size);
    }

    @Override
    public List<Staff> getAvailableDriver() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        detachedCriteria.add(Restrictions.eq("job", "司机"));
        detachedCriteria.add(Restrictions.eq("state", "等待业务"));
        return staffDao.search(detachedCriteria);
    }
}
