package com.logistics.dao.Impl;

import com.logistics.dao.GoodsDao;
import com.logistics.entity.Goods;
import org.springframework.stereotype.Repository;

/**
 * Created by Mklaus on 15/7/24.
 */
@Repository
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {
    @Override
    public Class classOfT(){
        return Goods.class;
    }
}
