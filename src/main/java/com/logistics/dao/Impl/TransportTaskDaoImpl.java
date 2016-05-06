package com.logistics.dao.Impl;

import com.logistics.dao.TransportTaskDao;
import com.logistics.entity.TransportTask;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Ranger on 2015/8/13.
 */
@Repository
public class TransportTaskDaoImpl extends BaseDaoImpl<TransportTask> implements TransportTaskDao {
    @Override
    public Class classOfT() {
        return TransportTask.class;
    }

    @Override
    public Integer save(TransportTask transportTask) {
        transportTask.setQueryId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        return super.save(transportTask);
    }
}
