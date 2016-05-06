package com.logistics.service;

import com.logistics.entity.Cargo;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
public interface CargoService extends BaseService<Cargo> {
    List<Cargo> getCargoByTaskId(int taskId);

    Integer add(Cargo cargo, Integer taskId);

    void update(Cargo cargo, Integer taskId);
}
