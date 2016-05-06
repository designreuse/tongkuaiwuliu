package com.logistics.service;

import com.logistics.entity.CarBusiness;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
public interface CarBusinessService extends BaseService<CarBusiness> {
    Integer add(Integer taskId, Integer vehicleId, CarBusiness carBusiness);

    List<CarBusiness> getCarBusinessByTaskId(int taskId);

    void update(Integer taskId, Integer vehicleId, CarBusiness carBusiness);
}
