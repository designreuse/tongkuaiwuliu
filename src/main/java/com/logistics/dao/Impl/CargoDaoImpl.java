package com.logistics.dao.Impl;

import com.logistics.dao.CargoDao;
import com.logistics.entity.Cargo;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository("cargoDaoImpl")
public class CargoDaoImpl extends BaseDaoImpl<Cargo> implements CargoDao {
    @Override
    public Class classOfT() {
        return Cargo.class;
    }
}
