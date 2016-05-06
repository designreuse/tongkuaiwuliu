package com.logistics.service;

import com.logistics.entity.OilBuy;

/**
 * Created by Mklaus on 15/8/21.
 */
public interface OilBuyService extends BaseService<OilBuy>{
    public Integer add(OilBuy oilBuy,Integer staffId,Integer vehicleId);
    public void update(OilBuy oilBuy,Integer staffId,Integer vehicleId);
}
