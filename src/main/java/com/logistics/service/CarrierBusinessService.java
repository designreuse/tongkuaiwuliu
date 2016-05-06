package com.logistics.service;

import com.logistics.entity.CarrierBusiness;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
public interface CarrierBusinessService extends BaseService<CarrierBusiness> {
    Integer add(CarrierBusiness carrierBusiness, Integer transTaskId, Integer companyId);

    boolean update(CarrierBusiness carrierBusiness, Integer transTaskId, Integer companyId);

    List<CarrierBusiness> getCarrierBusinessByTaskId(int taskId);
}
