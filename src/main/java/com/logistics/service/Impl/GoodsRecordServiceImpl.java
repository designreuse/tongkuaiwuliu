package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.GoodsRecordDao;
import com.logistics.entity.GoodsRecord;
import com.logistics.service.GoodsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ranger on 2015/8/14.
 */
@Service
public class GoodsRecordServiceImpl extends BaseServiceImpl<GoodsRecord> implements GoodsRecordService {
    @Autowired
    private GoodsRecordDao goodsRecordDao;

    @Override
    public BaseDao getBaseDao() {
        return goodsRecordDao;
    }
}
