package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.CarrierBusinessDao;
import com.logistics.dao.CompanyDao;
import com.logistics.dao.TransportTaskDao;
import com.logistics.entity.CarrierBusiness;
import com.logistics.service.CarrierBusinessService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service
public class CarrierBusinessServiceImpl extends BaseServiceImpl<CarrierBusiness> implements CarrierBusinessService {
    @Autowired
    private CarrierBusinessDao carrierBusinessDao;
    @Resource
    private TransportTaskDao transportTaskDao;
    @Resource
    private CompanyDao companyDao;

    @Override
    public BaseDao getBaseDao() {
        return carrierBusinessDao;
    }

    @Override
    public Integer add(CarrierBusiness carrierBusiness, Integer transTaskId, Integer companyId) {
        if (transTaskId != null) {
            carrierBusiness.setTask(transportTaskDao.get(transTaskId));
        }

        if (companyId != null) {
            carrierBusiness.setCompany(companyDao.get(companyId));
        }

        return this.add(carrierBusiness);
    }

    @Override
    public boolean update(CarrierBusiness carrierBusiness, Integer transTaskId, Integer companyId) {
        if (transTaskId != null) {
            carrierBusiness.setTask(transportTaskDao.get(transTaskId));
        }

        if (companyId != null) {
            carrierBusiness.setCompany(companyDao.get(companyId));
        }
        this.update(carrierBusiness);
        return true;
    }

    @Override
    public List<CarrierBusiness> getCarrierBusinessByTaskId(int taskId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CarrierBusiness.class);
        detachedCriteria.add(Restrictions.eq("task", transportTaskDao.get(taskId)));
        return carrierBusinessDao.search(detachedCriteria);
    }
}
