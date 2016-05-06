package com.logistics.service;

import com.logistics.entity.TransportTask;

import java.util.List;

/**
 * Created by Ranger on 2015/8/14.
 */
public interface TransportTaskService extends BaseService<TransportTask> {
    Integer add(TransportTask transportTask, Integer customerId);

    void update(TransportTask transportTask, Integer customerId);

    List<TransportTask> getTransportTaskByCusId(Integer cusId);

    Integer autoArrangeTask(TransportTask transportTask);
}
