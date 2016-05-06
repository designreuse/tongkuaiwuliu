package com.logistics.dao.Impl;

import com.logistics.dao.CarrierBusinessDao;
import com.logistics.entity.CarrierBusiness;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository("carrierBusinessDaoImpl")
public class CarrierBusinessDaoImpl extends BaseDaoImpl<CarrierBusiness> implements CarrierBusinessDao {
    @Override
    public Class classOfT() {
        return CarrierBusiness.class;
    }
}
