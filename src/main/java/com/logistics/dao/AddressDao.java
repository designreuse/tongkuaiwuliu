package com.logistics.dao;

import com.logistics.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mklaus on 15/8/24.
 */
public interface AddressDao extends BaseDao<Address> {
    public List<Address> get(Integer start, Integer size);
}
