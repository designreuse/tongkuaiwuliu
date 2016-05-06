package com.logistics.dao.Impl;

import com.logistics.dao.OilBuyDao;
import com.logistics.entity.OilBuy;
import org.springframework.stereotype.Repository;

/**
 * Created by Mklaus on 15/8/21.
 */
@Repository
public class OilBuyDaoImpl extends BaseDaoImpl<OilBuy> implements OilBuyDao {
    @Override
    public Class classOfT() {
        return OilBuy.class;
    }
}
