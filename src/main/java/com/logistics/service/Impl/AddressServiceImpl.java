package com.logistics.service.Impl;

import com.logistics.dao.AddressDao;
import com.logistics.dao.BaseDao;
import com.logistics.entity.Address;
import com.logistics.service.AddressService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mklaus on 15/8/24.
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService {
    @Resource
    private AddressDao addressDao;

    @Override
    public BaseDao getBaseDao() {
        return this.addressDao;
    }

    @Override
    public Integer add(Address address, Integer parent) {
        if (parent != null){
            address.setParent(addressDao.get(parent));
        }

        return this.add(address);
    }

    @Override
    public List<Address> getByLevel(Integer level) {
        DetachedCriteria dc = DetachedCriteria.forClass(Address.class);

        dc.add(Restrictions.eq("level",level));

        List<Address> l = addressDao.search(dc);

        return l;
    }
}

