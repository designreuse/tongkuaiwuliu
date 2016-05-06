package com.logistics.service;

import com.logistics.entity.Vehicle;

/**
 * Created by Ranger on 2015/8/14.
 */
public interface VehicleService extends BaseService<Vehicle> {
    Integer add(Vehicle vehicle, Integer driverId, Integer vehicleTypeId);
    void update(Vehicle vehicle, Integer driverId, Integer vehicleTypeId);
}
