package com.logistics.dao.Impl;

import com.logistics.dao.AddressDao;
import com.logistics.entity.Address;
import org.springframework.stereotype.Repository;

/**
 * Created by Mklaus on 15/8/24.
 */
@Repository
public class AddressDaoImpl extends BaseDaoImpl<Address> implements AddressDao{
    @Override
    public Class classOfT() {
        return Address.class;
    }

//    @Override
//    public List<Address> get(Integer start, Integer size) {
//
//        List<Address> l = createQuery("from Address addr")
//                .setFirstResult(start)
//                .setMaxResults(size)
//                .list();
//
//        return l;
//    }
}
