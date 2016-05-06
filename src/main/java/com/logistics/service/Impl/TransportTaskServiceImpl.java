package com.logistics.service.Impl;

import com.logistics.dao.*;
import com.logistics.entity.*;
import com.logistics.service.TransportTaskService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Ranger on 2015/8/14.
 */
@Repository
public class TransportTaskServiceImpl extends BaseServiceImpl<TransportTask> implements TransportTaskService {
    @Autowired
    private TransportTaskDao transportTaskDao;
    @Autowired
    private CargoDao cargoDao;
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private CarBusinessDao carBusinessDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private StaffDao staffDao;

    @Override
    public BaseDao getBaseDao() {
        return transportTaskDao;
    }

    @Override
    public Integer autoArrangeTask(TransportTask transportTask) {
        // 暂存运输单中的货物数据
        Set<Cargo> cargoSet = transportTask.getCargoSet();
        // 计算出货物的总重量
        int totalWeight = 0;
        for (Cargo cargo : cargoSet) {
            totalWeight += cargo.getWeight();
        }
        // 检测库存中是否有足够的适运车辆和人员
        List<Vehicle> vehicleList = vehicleDao.getAll();
        // 计算最大运量
        int maximumLoad = 0;
        // 因为在下面有删除操作,因此使用Iterator
        Iterator<Vehicle> iterator = vehicleList.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getDriver().getState().equals("等待业务")) {
                maximumLoad += vehicle.getVehicleType().getCapacity();
                continue;
            }
            if (vehicle.getDriver().getState().equals("业务中")) {
                DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CarBusiness.class);
                detachedCriteria.add(Restrictions.eq("vehicle", vehicle));
                List<CarBusiness> carBusinessList = carBusinessDao.search(detachedCriteria);
                long leastFinishingDate = Long.MIN_VALUE;
                // 获取最近的任务完成日期
                for (CarBusiness carBusiness : carBusinessList) {
                    long dateOfFinishing = carBusiness.getDateOfFinishing();
                    if (dateOfFinishing > leastFinishingDate) {
                        leastFinishingDate = dateOfFinishing;
                    }
                }
                if (leastFinishingDate < transportTask.getStartDate()) {
                    maximumLoad += vehicle.getVehicleType().getCapacity();
                } else {
                    iterator.remove();
                }
            }
        }
        if (maximumLoad >= totalWeight) { // 自营
            transportTask.setType(0);
            // 安排运输车辆
            arrangeVehicles(transportTask, totalWeight, (ArrayList<Vehicle>) vehicleList);
        } else { // 委托承运
            transportTask.setType(1);
        }
        // 更新运输单数据
        transportTaskDao.update(transportTask);
        return transportTask.getId();
    }

    private void arrangeVehicles(TransportTask transportTask, int carriedLoad, ArrayList<Vehicle> availableVehicles) {
        // 将汽车的运量从小到大进行排序
        Collections.sort(availableVehicles, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.getVehicleType().getCapacity() - o2.getVehicleType().getCapacity();
            }
        });
        // 按运量从小到大安排运输
        for (Vehicle vehicle : availableVehicles) {
            carriedLoad -= vehicle.getVehicleType().getCapacity();
            // 设置并保存运输记录
            CarBusiness carBusiness = new CarBusiness(vehicle, transportTask);
            carBusiness.setDateOfStarting(transportTask.getStartDate());
            carBusiness.setDateOfFinishing(transportTask.getEndDate());
            // 更改司机的状态
            Staff driver = vehicle.getDriver();
            driver.setState("业务中");
            staffDao.update(driver);
            carBusinessDao.save(carBusiness);
            // 安排完任务后跳出循环
            if (carriedLoad <= 0) {
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        // 将相关联的货物记录删除
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Cargo.class);
        detachedCriteria.add(Restrictions.eq("task", transportTaskDao.get(id)));
        List<Cargo> cargoList = cargoDao.search(detachedCriteria);
        for (Cargo cargo : cargoList) {
            cargoDao.delete(cargo);
        }
        super.delete(id);
    }

    @Override
    public Integer add(TransportTask transportTask, Integer customerId) {
        if (customerId != null) {
            transportTask.setCustomer(userDao.get(customerId));
        }
        return this.add(transportTask);
    }

    @Override
    public void update(TransportTask transportTask, Integer customerId) {
        // 将属性设置入transportTask
        if (customerId != null) {
            transportTask.setCustomer(userDao.get(customerId));
        }
        update(transportTask);
    }

    @Override
    public List<TransportTask> getTransportTaskByCusId(Integer cusId) {
        if (cusId != null) {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TransportTask.class);
            detachedCriteria.add(Restrictions.eq("customer", userDao.get(cusId)));
            return transportTaskDao.search(detachedCriteria);
        } else {
            return null;
        }
    }
}