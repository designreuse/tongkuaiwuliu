package com.logistics.service;

import com.logistics.entity.Address;
import com.logistics.entity.Admin;

import java.util.List;

/**
 * Created by Mklaus on 15/8/24.
 */
public interface AddressService extends BaseService<Address>{
    public Integer add(Address address,Integer parent);

    public List<Address> getByLevel(Integer level);

}
