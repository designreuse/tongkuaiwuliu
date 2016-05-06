package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.OilBuyDao;
import com.logistics.dao.StaffDao;
import com.logistics.dao.VehicleDao;
import com.logistics.entity.OilBuy;
import com.logistics.service.OilBuyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Mklaus on 15/8/21.
 */
@Service
public class OilBuyServiceImpl extends BaseServiceImpl<OilBuy> implements OilBuyService {
    @Resource
    private OilBuyDao oilBuyDao;
    @Resource
    private StaffDao staffDao;
    @Resource
    private VehicleDao vehicleDao;

    @Override
    public BaseDao getBaseDao() {
        return oilBuyDao;
    }

    @Override
    public Integer add(OilBuy oilBuy, Integer staffId, Integer vehicleId) {
        if (staffId != null){
            oilBuy.setStaff(staffDao.get(staffId));
        }

        if (vehicleId != null){
            oilBuy.setVehicle(vehicleDao.get(vehicleId));
        }

        return this.add(oilBuy);
    }

    @Override
    public void update(OilBuy oilBuy, Integer staffId, Integer vehicleId){
        if (staffId != null){
            oilBuy.setStaff(staffDao.get(staffId));
        }

        if (vehicleId != null){
            oilBuy.setVehicle(vehicleDao.get(vehicleId));
        }
        this.update(oilBuy);
    }
}
