package com.logistics.dao.Impl;

import com.logistics.dao.GoodsRecordDao;
import com.logistics.entity.GoodsRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository
public class GoodsRecordDaoImpl extends BaseDaoImpl<GoodsRecord> implements GoodsRecordDao {
    @Override
    public Class classOfT() {
        return GoodsRecord.class;
    }
}
