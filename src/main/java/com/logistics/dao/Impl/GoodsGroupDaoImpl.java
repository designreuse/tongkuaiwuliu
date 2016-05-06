package com.logistics.dao.Impl;

import com.logistics.dao.GoodsGroupDao;
import com.logistics.entity.GoodsGroup;
import org.springframework.stereotype.Repository;

/**
 * Created by Mklaus on 15/8/20.
 */
@Repository
public class GoodsGroupDaoImpl extends BaseDaoImpl<GoodsGroup> implements GoodsGroupDao {
    @Override
    public Class classOfT() {
        return GoodsGroup.class;
    }
}
