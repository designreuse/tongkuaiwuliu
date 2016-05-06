package com.logistics.service.Impl;

import com.logistics.dao.BaseDao;
import com.logistics.dao.GoodsDao;
import com.logistics.entity.Goods;
import com.logistics.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mklaus on 15/7/24.
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService{
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public BaseDao getBaseDao() {
        return this.goodsDao;
    }
}
